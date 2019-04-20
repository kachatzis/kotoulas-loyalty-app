package com.journeyapps.barcodescanner;

public class Size implements Comparable<Size> {
    public final int height;
    public final int width;

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public Size rotate() {
        return new Size(this.height, this.width);
    }

    public Size scale(int i, int i2) {
        return new Size((this.width * i) / i2, (this.height * i) / i2);
    }

    public Size scaleFit(Size size) {
        int i = this.width;
        int i2 = size.height;
        int i3 = i * i2;
        size = size.width;
        int i4 = this.height;
        if (i3 >= size * i4) {
            return new Size(size, (i4 * size) / i);
        }
        return new Size((i * i2) / i4, i2);
    }

    public Size scaleCrop(Size size) {
        int i = this.width;
        int i2 = size.height;
        int i3 = i * i2;
        size = size.width;
        int i4 = this.height;
        if (i3 <= size * i4) {
            return new Size(size, (i4 * size) / i);
        }
        return new Size((i * i2) / i4, i2);
    }

    public boolean fitsIn(Size size) {
        return (this.width > size.width || this.height > size.height) ? null : true;
    }

    public int compareTo(Size size) {
        int i = this.height * this.width;
        int i2 = size.height * size.width;
        if (i2 < i) {
            return 1;
        }
        return i2 > i ? -1 : null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.width);
        stringBuilder.append("x");
        stringBuilder.append(this.height);
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                Size size = (Size) obj;
                if (this.width != size.width) {
                    return false;
                }
                if (this.height != size.height) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }
}
