package cbir;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BMPLoader

{
	
	public static int constructInt(byte[] in, int offset) {

		int ret = ((int) in[offset + 3] & 0xff);

		ret = (ret << 8) | ((int) in[offset + 2] & 0xff);

		ret = (ret << 8) | ((int) in[offset + 1] & 0xff);

		ret = (ret << 8) | ((int) in[offset + 0] & 0xff);

		return (ret);

	}

	

	public static int constructInt3(byte[] in, int offset) {

		int ret = 0xff;

		ret = (ret << 8) | ((int) in[offset + 2] & 0xff);

		ret = (ret << 8) | ((int) in[offset + 1] & 0xff);

		ret = (ret << 8) | ((int) in[offset + 0] & 0xff);

		return (ret);

	}

	// build an int from a byte array - convert little to big endian

	public static long constructLong(byte[] in, int offset) {

		long ret = ((long) in[offset + 7] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 6] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 5] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 4] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 3] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 2] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 1] & 0xff);

		ret |= (ret << 8) | ((long) in[offset + 0] & 0xff);

		return (ret);

	}

	// build an double from a byte array - convert little to big endian

	public static double constructDouble(byte[] in, int offset) {

		long ret = constructLong(in, offset);

		return (Double.longBitsToDouble(ret));

	}

	// build an short from a byte array - convert little to big endian

	public static short constructShort(byte[] in, int offset) {

		short ret = (short) ((short) in[offset + 1] & 0xff);

		ret = (short) ((ret << 8) | (short) ((short) in[offset + 0] & 0xff));

		return (ret);

	}

	// internal class representing a bitmap header structure

	// with code to read it from a file

	static class BitmapHeader {

		public int nsize;

		public int nbisize;

		public int nwidth;

		public int nheight;

		public int nplanes;

		public int nbitcount;

		public int ncompression;

		public int nsizeimage;

		public int nxpm;

		public int nypm;

		public int nclrused;

		public int nclrimp;

		// read in the bitmap header

		public void read(FileInputStream fs) throws IOException

		{

			final int bflen = 14; // 14 byte BITMAPFILEHEADER

			byte bf[] = new byte[bflen];

			fs.read(bf, 0, bflen);

			final int bilen = 40; // 40-byte BITMAPINFOHEADER

			byte bi[] = new byte[bilen];

			fs.read(bi, 0, bilen);

			// Interperet data.

			nsize = constructInt(bf, 2);

			// System.out.println("File type is :"+(char)bf[0]+(char)bf[1]);

			// System.out.println("Size of file is :"+nsize);

			nbisize = constructInt(bi, 2);

			// System.out.println("Size of bitmapinfoheader is :"+nbisize);

			nwidth = constructInt(bi, 4);

			// System.out.println("Width is :"+nwidth);

			nheight = constructInt(bi, 8);

			// System.out.println("Height is :"+nheight);

			nplanes = constructShort(bi, 12); // (((int)bi[13]&0xff)<<8) |
			// (int)bi[12]&0xff;

			// System.out.println("Planes is :"+nplanes);

			nbitcount = constructShort(bi, 14); // (((int)bi[15]&0xff)<<8) |
			// (int)bi[14]&0xff;

			// System.out.println("BitCount is :"+nbitcount);

			// Look for non-zero values to indicate compression

			ncompression = constructInt(bi, 16);

			// System.out.println("Compression is :"+ncompression);

			nsizeimage = constructInt(bi, 20);

			// System.out.println("SizeImage is :"+nsizeimage);

			nxpm = constructInt(bi, 24);

			// System.out.println("X-Pixels per meter is :"+nxpm);

			nypm = constructInt(bi, 28);

			// System.out.println("Y-Pixels per meter is :"+nypm);

			nclrused = constructInt(bi, 32);

			// System.out.println("Colors used are :"+nclrused);

			nclrimp = constructInt(bi, 36);

			// System.out.println("Colors important are :"+nclrimp);

		}

	}

	public static Image read(FileInputStream fs)

	{

		try {

			BitmapHeader bh = new BitmapHeader();

			bh.read(fs);

			if (bh.nbitcount == 24)

				return (readMap24(fs, bh));

			if (bh.nbitcount == 32)

				return (readMap32(fs, bh));

			if (bh.nbitcount == 8)

				return (readMap8(fs, bh));

			fs.close();

		}

		catch (IOException e)

		{

			// System.out.println("Caught exception in loadbitmap!");

		}

		return (null);

	}


	protected static Image readMap32(FileInputStream fs, BitmapHeader bh)
			throws IOException

	{

		Image image;

		// No Palatte data for 24-bit format but scan lines are

		// padded out to even 4-byte boundaries.

		int xwidth = bh.nsizeimage / bh.nheight;

		int ndata[] = new int[bh.nheight * bh.nwidth];

		byte brgb[] = new byte[bh.nwidth * 4 * bh.nheight];

		fs.read(brgb, 0, bh.nwidth * 4 * bh.nheight);

		int nindex = 0;

		for (int j = 0; j < bh.nheight; j++)

		{

			for (int i = 0; i < bh.nwidth; i++)

			{

				ndata[bh.nwidth * (bh.nheight - j - 1) + i] = constructInt3(
						brgb, nindex);

				nindex += 4;

			}

		}

		image = Toolkit.getDefaultToolkit().createImage

		(new MemoryImageSource(bh.nwidth, bh.nheight,

		ndata, 0, bh.nwidth));

		fs.close();

		return (image);

	}

	

	protected static Image readMap24(FileInputStream fs, BitmapHeader bh)
			throws IOException

	{

		Image image;

		// No Palatte data for 24-bit format but scan lines are

		// padded out to even 4-byte boundaries.

		int npad = (bh.nsizeimage / bh.nheight) - bh.nwidth * 3;

		int ndata[] = new int[bh.nheight * bh.nwidth];

		byte brgb[] = new byte[(bh.nwidth + npad) * 3 * bh.nheight];

		fs.read(brgb, 0, (bh.nwidth + npad) * 3 * bh.nheight);

		int nindex = 0;

		for (int j = 0; j < bh.nheight; j++)

		{

			for (int i = 0; i < bh.nwidth; i++)

			{

				ndata[bh.nwidth * (bh.nheight - j - 1) + i] = constructInt3(
						brgb, nindex);

				nindex += 3;

			}

			nindex += npad;

		}

		image = Toolkit.getDefaultToolkit().createImage

		(new MemoryImageSource(bh.nwidth, bh.nheight,

		ndata, 0, bh.nwidth));

		fs.close();

		return (image);

	}

	

	protected static Image readMap8(FileInputStream fs, BitmapHeader bh)
			throws IOException

	{

		Image image;

		// Have to determine the number of colors, the clrsused

		// parameter is dominant if it is greater than zero. If

		// zero, calculate colors based on bitsperpixel.

		int nNumColors = 0;

		if (bh.nclrused > 0)

		{

			nNumColors = bh.nclrused;

		}

		else

		{

			nNumColors = (1 & 0xff) << bh.nbitcount;

		}

		// System.out.println("The number of Colors is"+nNumColors);

		// Some bitmaps do not have the sizeimage field calculated

		// Ferret out these cases and fix 'em.

		if (bh.nsizeimage == 0)

		{

			bh.nsizeimage = ((((bh.nwidth * bh.nbitcount) + 31) & ~31) >> 3);

			bh.nsizeimage *= bh.nheight;

			// System.out.println("nsizeimage (backup) is"+nsizeimage);

		}

		// Read the palatte colors.

		int npalette[] = new int[nNumColors];

		byte bpalette[] = new byte[nNumColors * 4];

		fs.read(bpalette, 0, nNumColors * 4);

		int nindex8 = 0;

		for (int n = 0; n < nNumColors; n++)

		{

			npalette[n] = constructInt3(bpalette, nindex8);

			nindex8 += 4;

		}

		// Read the image data (actually indices into the palette)

		// Scan lines are still padded out to even 4-byte

		// boundaries.

		int npad8 = (bh.nsizeimage / bh.nheight) - bh.nwidth;

		// System.out.println("nPad is:"+npad8);

		int ndata8[] = new int[bh.nwidth * bh.nheight];

		byte bdata[] = new byte[(bh.nwidth + npad8) * bh.nheight];

		fs.read(bdata, 0, (bh.nwidth + npad8) * bh.nheight);

		nindex8 = 0;

		for (int j8 = 0; j8 < bh.nheight; j8++)

		{

			for (int i8 = 0; i8 < bh.nwidth; i8++)

			{

				ndata8[bh.nwidth * (bh.nheight - j8 - 1) + i8] =

				npalette[((int) bdata[nindex8] & 0xff)];

				nindex8++;

			}

			nindex8 += npad8;

		}

		image = Toolkit.getDefaultToolkit().createImage

		(new MemoryImageSource(bh.nwidth, bh.nheight,

		ndata8, 0, bh.nwidth));

		return (image);

	}

	

	public static Image load(String sdir, String sfile) {

		return (load(sdir + sfile));

	}

	
	

	public static Image load(String sdir)

	{

		try

		{

			FileInputStream fs = new FileInputStream(sdir);

			return (read(fs));

		}

		catch (IOException ex) {

			return (null);

		}

	}

	BMPLoader(String a) throws FileNotFoundException {
		String s = a;
		FileInputStream in = new FileInputStream(s);

		Image TheImage = read(in);

		

	}

	BMPLoader() {
		super();
	}

	// end class BMPLoader

}
