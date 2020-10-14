package lesani.image.xdatahiding.runlength.core;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 22, 2005
 * Time: 11:13:13 PM
 */

public class ImagePanel extends JPanel
{
	private BufferedImage image;

	public ImagePanel(BufferedImage bufferedImage)
	{
		this.image = bufferedImage;
	}

	public ImagePanel()
	{
		this.image = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(image.getWidth(), image.getHeight());
		//return new Dimension(1000, 1000);
	}

}
