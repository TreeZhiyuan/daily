/**
 * Company: www.baofu.com
 * @author dasheng(大圣)
 * @date 2018年1月25日
 */
package com.example.daily.baofoo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.example.daily.baofoo.rsa.RsaCodingUtil;
import com.example.daily.baofoo.rsa.SignatureUtils;
import com.example.daily.baofoo.util.FormatUtil;
import com.example.daily.baofoo.util.HttpUtil;
import com.example.daily.baofoo.util.Log;
import com.example.daily.baofoo.util.SecurityUtil;

public class QueryBind{
	/**
	 * 绑卡查询
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String send_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//报文发送日期时间		
		String  pfxpath ="D:\\baofoo_conf\\bfkey_100025773@@200001173.pfx";//商户私钥        
        String  cerpath = "D:\\baofoo_conf\\bfkey_100025773@@200001173.cer";//宝付公钥
        
        String AesKey = "4f66405c4f66405c";////商户自定义（可随机生成  商户自定义(AES key长度为=16位)）
		String dgtl_envlp = "01|"+AesKey;//使用接收方的公钥加密后的对称密钥，并做Base64转码，明文01|对称密钥，01代表AES[密码商户自定义]
		System.out.println("密码dgtl_envlp："+dgtl_envlp);		
		dgtl_envlp = RsaCodingUtil.encryptByPubCerFile(SecurityUtil.Base64Encode(dgtl_envlp), cerpath);//公钥加密	
		String AccNo = "6222032010004709320";//银行卡号
		System.out.println("银行卡号："+AccNo);		
		AccNo = SecurityUtil.AesEncrypt(SecurityUtil.Base64Encode(AccNo), AesKey);//先BASE64后进行AES加密
		System.out.println("银行卡号AES结果:"+AccNo);

		Map<String,String> DateArry = new TreeMap<String,String>();
		DateArry.put("send_time", send_time);
		DateArry.put("msg_id", "TISN"+System.currentTimeMillis());//报文流水号
		DateArry.put("version", "4.0.0.0");
		DateArry.put("terminal_id", "200001173");
		DateArry.put("txn_type", "03");//交易类型
		DateArry.put("member_id", "100025773");
		DateArry.put("dgtl_envlp", dgtl_envlp);
		DateArry.put("user_id", "T11730011013");//用户在平台的唯一ID
		DateArry.put("acc_no", "");//银行卡号密文[与user_id必须其中一个有值]
		
		String SignVStr = FormatUtil.coverMap2String(DateArry);
		System.out.println("SHA-1摘要字串："+SignVStr);
		String signature = SecurityUtil.sha1X16(SignVStr, "UTF-8");//签名
		System.out.println("SHA-1摘要结果："+signature);		
		String Sign = SignatureUtils.encryptByRSA(signature, pfxpath, "100025773_286941");
		System.out.println("RSA签名结果："+Sign);		
		DateArry.put("signature", Sign);//签名域
		
		String PostString  = HttpUtil.RequestForm("https://vgw.baofoo.com/cutpayment/protocol/backTransRequest", DateArry);	
		System.out.println("请求返回:"+PostString);
		
		Map<String, String> ReturnData = FormatUtil.getParm(PostString);
		
		if(!ReturnData.containsKey("signature")){
			throw new Exception("缺少验签参数！");
		}
		
		String RSign=ReturnData.get("signature");
		System.out.println("返回的验签值："+RSign);
		ReturnData.remove("signature");//需要删除签名字段		
		String RSignVStr = FormatUtil.coverMap2String(ReturnData);
		System.out.println("返回SHA-1摘要字串："+RSignVStr);
		String RSignature = SecurityUtil.sha1X16(RSignVStr, "UTF-8");//签名
		System.out.println("返回SHA-1摘要结果："+RSignature);
		
		if(SignatureUtils.verifySignature(cerpath,RSignature,RSign)){
			System.out.println("Yes");//验签成功
		}
		if(!ReturnData.containsKey("resp_code")){
			throw new Exception("缺少resp_code参数！");
		}
		if(ReturnData.get("resp_code").toString().equals("S")){	
			if(!ReturnData.containsKey("dgtl_envlp")){
				throw new Exception("缺少dgtl_envlp参数！");
			}
			String RDgtlEnvlp = SecurityUtil.Base64Decode(RsaCodingUtil.decryptByPriPfxFile(ReturnData.get("dgtl_envlp"), pfxpath, "100025773_286941"));		
			System.out.println("返回数字信封："+RDgtlEnvlp);
			String RAesKey=FormatUtil.getAesKey(RDgtlEnvlp);//获取返回的AESkey
			System.out.println("返回的AESkey:"+RAesKey);
			System.out.println("协议列表:"+SecurityUtil.Base64Decode(SecurityUtil.AesDecrypt(ReturnData.get("protocols"),RAesKey)));
		}
	}
}