package com.example.daily.watermark;

import static org.junit.Assert.fail;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: zhiyuan
 * @date: 2018-05-31
 * @project: daily
 * @description:
 */

public class WaterMarkTester {
    final String targetImageUrl = "F://pic//8e8fb3f192aa4538843414e6050a6996.jpg";
    final String waterImgUrl = "F://pic//water.png";
    final String destWaterImgUrl = "F://pic//targetWaterImage.jpg";
    final String destImageUrl = "F://pic//TreeHugger_ZH-CN10397384095_1920x1080.jpg";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    @Test
    public void backImageFiles() throws IOException {
        copyFileUsingFileChannels(new File(targetImageUrl), new File(destImageUrl));
    }

    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }

    }

    /**
     * 添加图片水印
     * 
     * @throws IOException
     */
    @Test
    public void testAddWaterMark() throws IOException {
        copyFileUsingFileChannels(new File(targetImageUrl), new File(destImageUrl));
        copyFileUsingFileChannels(new File(waterImgUrl), new File(destWaterImgUrl));
        Image waterImage = ImageIO.read(new File(targetImageUrl));
        int targetWidth = waterImage.getWidth(null);

        Image waterImage2 = ImageIO.read(new File(destWaterImgUrl));
        int waterHeight = waterImage2.getHeight(null);
        // File file = ImageUtils.resize(new File(destWaterImgUrl), waterHeight, 6 *
        // targetWidth / 20, false);
        // ImageUtils.pressImage(new FileInputStream(new File(destImageUrl)), file,
        // Integer.MAX_VALUE, 0, 1f);
        ImageUtils.pressImage(new FileInputStream(new File(destImageUrl)),
                new File(destWaterImgUrl), Integer.MAX_VALUE, 0, 1f);
    }

    /**
     * 重新设置图片大小
     * 
     * @throws IOException
     */
    @Test
    public void testResizeWaterImage() throws IOException {
        copyFileUsingFileChannels(new File(targetImageUrl), new File(destImageUrl));
        copyFileUsingFileChannels(new File(waterImgUrl), new File(destWaterImgUrl));
        Image waterImage = ImageIO.read(new File(targetImageUrl));
        int width_1 = waterImage.getWidth(null);

        Image waterImage2 = ImageIO.read(new File(destWaterImgUrl));
        int height_1 = waterImage2.getHeight(null);
        ImageUtils.resize(new File(destWaterImgUrl), height_1, 6 * width_1 / 20, false);
    }

    /**
     * 删除测试产生的临时图片
     * 
     * @throws IOException
     */
    @Test
    public void deleteBackImageFiles() throws IOException {
        File f = new File(destImageUrl);
        f.delete();
        File f1 = new File(destWaterImgUrl);
        f1.delete();
    }

    @Test
    public void deleteBackImageFiles2() throws IOException {
        String url = "/F:/workspace-ox/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/hotchain-console/WEB-INF/classes/water.png";
        String[] pros = url.split("/");
    }
}
