package org.tongji.programming.helper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图像相关的工具类
 *
 * @author cineazhan
 */
public class ImageHelper {
    public static BufferedImage removeAlpha(BufferedImage source) {
        // 检查原始图像是否包含 alpha 通道
        if (!source.getColorModel().hasAlpha()) {
            // 如果不包含 alpha 通道，直接返回原图
            return source;
        }

        // 创建一个不带透明度的新图像
        BufferedImage newImage = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);

        // 获取画笔并将原图绘制到新图像上
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(source, 0, 0, null);
        graphics.dispose();

        return newImage;
    }
}
