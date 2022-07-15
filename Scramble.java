import java.awt.Color;
import java.io.File;

/**
 * Scramble program that visually encrypts a photo.
 * Features symmetric encryption.
 * @author Chase Coleman
 * @version 7/15/22
 */

public class Scramble {

    public static void main(String[] args) {
        // EDIT ONLY LINES 15 AND 16 WITH THE ABSOLUTE FILE PATHS THEN RUN :)
        File input = new File("pictures/inputFileName.jpg");
        File output = new File("pictures/outputFileName.png");
        scramble(input, output);
        System.out.println("Picture encrypted successfully");
    }

    public static Picture scramble(File input, File file) {
        Picture pic = new Picture(input);
        pic = shuffleCol(pic);
        pic = shuffleRow(pic);
        pic = shuffleColor(pic);
        pic.save(file);
        return pic;
    }

    public static Picture shuffleCol(Picture pic) {
        int[] order = getScramArray(pic.width());
        int len = order.length;
        Color[][] pixels = colorArrayCol(pic);
        Color[][] scramCol = pixels.clone();
        for (int i = 0; i < len; i++) {
            scramCol[order[i]] = pixels[i];
        }
        return toPicCol(scramCol, pic);
    }

    public static Picture shuffleRow(Picture pic) {
        int[] order = getScramArray(pic.height());
        int len = order.length;
        Color[][] pixels = colorArrayRow(pic);
        Color[][] scramCol = pixels.clone();
        for (int i = 0; i < len; i++) {
            scramCol[order[i]] = pixels[i];
        }
        return toPicRow(scramCol, pic);
    }

    public static Picture shuffleColor(Picture pic) {
        int[] scram = getScramArray(256);
        Picture shuffled = new Picture(pic);
        for (int col = 0; col < pic.width(); col++) {
            for (int row = 0; row < pic.height(); row++) {
                Color defColor = pic.get(col, row);
                Color shuffColor = new Color(scram[defColor.getRed()], scram[defColor.getGreen()], scram[defColor.getBlue()]);
                shuffled.set(col, row, shuffColor);
            }
        }
        return shuffled;
    }
    
    public static Picture toPicCol(Color[][] pixels, Picture pic) {
        int width = pixels.length;
        int height = pixels[0].length;
        Picture picOut = new Picture(pic.width(), pic.height());
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                picOut.set(col, row, pixels[col][row]);
            }
        }

        for (int col = width; col < pic.width(); col++) {
            for (int row = height; row < pic.height(); row++) {
                picOut.set(col, row, pic.get(col, row));
            }
        }
        return picOut;
    }

    public static Picture toPicRow(Color[][] pixels, Picture pic) {
        int width = pixels[0].length;
        int height = pixels.length;
        Picture picOut = new Picture(pic.width(), pic.height());
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pic.set(col, row, pixels[row][col]);
            }
        }

        for (int row = height; row < pic.height(); row++) {
            for (int col = width; col < pic.width(); col++) {
                picOut.set(col, row, pic.get(col, row));
            }
        }

        return pic;
    }

    public static Color[][] colorArrayCol(Picture pic) {
        int width = pic.width();
        int height = pic.height();
        Color[][] pixels = new Color[width][height];
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color temp = pic.get(col, row);
                int r = temp.getRed();
                int g = temp.getGreen();
                int b = temp.getBlue();
                Color other = new Color(r, g, b);
                pixels[col][row] = other;
            }
        }
        return pixels;
    }

    public static Color[][] colorArrayRow(Picture pic) {
        int height = pic.height();
        int width = pic.width();
        Color[][] pixels = new Color[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color temp = pic.get(col, row);
                int r = temp.getRed();
                int g = temp.getGreen();
                int b = temp.getBlue();
                Color other = new Color(r, g, b);
                pixels[row][col] = other;
            }
        }
        return pixels;
    }

    public static int[] getScramArray(int length) {
        int[] arr = new int[length];
        int unfilled = length;
        int index = 0;
        int startIndex = 0;
        // find the biggest squared array possible
        while (unfilled > 0) {
            if (unfilled >= 25) {
                // using squares
                int square = (int) Math.floor(Math.sqrt(unfilled));
                if (square > 20) {
                    square = 20;
                }
                int[] guide = arrays(square);

                for (int i = 0; i < square * square; i++) {
                    int section = guide[i / square];
                    int place = guide[i % square];
                    arr[index] = section * square + place + startIndex;
                    index++;
                    unfilled--;
                }
                startIndex = index;

            } else if (unfilled >= 5) {
                // using regular arrays
                int[] guide = arrays(unfilled);
                for (int i = 0; i < guide.length; i++) {
                    int place = guide[i];
                    arr[index] = place + startIndex;
                    index++;
                    unfilled--;
                }

            } else {
                // finisher... less than five
                // perhaps have extra be split onto the ends
                arr[index] = index;
                index++;
                unfilled--;
            }
        }

        return arr;
    }

    public static int[] arrays(int length) {
        int[][] arrays = new int[16][];
        int[] five = {2, 4, 0, 3, 1};
        arrays[0] = five;
        int[] six = {2, 4, 0, 5, 1, 3};
        arrays[1] = six;
        int[] seven = {5, 1, 4, 6, 2, 0, 3};
        arrays[2] = seven;
        int[] eight = {3, 6, 4, 0, 2, 7, 1, 5};
        arrays[3] = eight;
        int[] nine = {4, 7, 2, 6, 0, 8, 3, 1, 5};
        arrays[4] = nine;
        int[] ten = {7, 4, 6, 9, 1, 8, 2, 0, 5, 3};
        arrays[5] = ten;
        int[] eleven = {5, 9, 6, 8, 4, 0, 2, 10, 3, 1, 7};
        arrays[6] = eleven;
        int[] twelve = {6, 9, 4, 8, 2, 10, 0, 11, 3, 1, 5, 7};
        arrays[7] = twelve;
        int[] thirteen = {11, 8, 6, 9, 7, 12, 2, 4, 1, 3, 10, 0, 5};
        arrays[8] = thirteen;
        int[] fourteen = {9, 5, 11, 13, 7, 1, 12, 4, 10, 0, 8, 2, 6, 3};
        arrays[9] = fourteen;
        int[] fifteen = {11, 4, 7, 3, 1, 12, 9, 2, 13, 6, 14, 0, 5, 8, 10};
        arrays[10] = fifteen;
        int[] sixteen = {7, 14, 5, 12, 9, 2, 13, 0, 10, 4, 8, 15, 3, 6, 1, 11};
        arrays[11] = sixteen;
        int[] seventeen = {5, 11, 16, 14, 8, 0, 13, 7, 4, 12, 15, 1, 9, 6, 3,
                10, 2};
        arrays[12] = seventeen;
        int[] eighteen = {6, 14, 11, 9, 7, 16, 0, 4, 13, 3, 17, 2, 15, 8, 1, 12,
                5, 10};
        arrays[13] = eighteen;
        int[] nineteen = {11, 6, 12, 15, 10, 18, 1, 14, 17, 13, 4, 0, 2, 9, 7,
                3, 16, 8, 5};
        arrays[14] = nineteen;
        int[] twenty = {13, 7, 5, 15, 18, 2, 11, 1, 19, 16, 12, 6, 10, 0, 17, 3,
                9, 14, 4, 8};
        arrays[15] = twenty;
        if (length > 20) {
            return arrays[15];
        }
        return arrays[length - 5];
    }

}

