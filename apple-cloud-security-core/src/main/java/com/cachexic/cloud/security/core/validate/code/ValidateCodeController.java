package com.cachexic.cloud.security.core.validate.code;

import com.cachexic.cloud.security.core.properties.SecurityProperties;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author tangmin
 * @Description: 校验码controller
 * @date 2017-09-29 13:29:52
 */
@RestController
public class ValidateCodeController {

  public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

  @Autowired
  private SecurityProperties securityProperties;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @GetMapping("/code/image")
  public void codeImage(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    //1.创建图片验证码对象
    ImageCode imageCode = generate(new ServletWebRequest(request));
    //2.向session中存放图片验证码对象,指定key
    sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
    //3.把图片写到响应中
    ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
  }

  private ImageCode generate(ServletWebRequest request) {
    int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",
        securityProperties.getCode().getImage().getWidth());
    int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",
        securityProperties.getCode().getImage().getHeight());
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics g = image.getGraphics();

    Random random = new Random();

    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);
    g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 150; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }

    String sRand = "";
    for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
      String rand = String.valueOf(random.nextInt(10));
      sRand += rand;
      g.setColor(
          new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      g.drawString(rand, 13 * i + 6, 16);
    }
    g.dispose();

    return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
  }

  /**
   * 生成随机背景条纹
   */
  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

}