package android.support.design.animation;

import android.animation.TypeEvaluator;

public class ArgbEvaluatorCompat implements TypeEvaluator<Integer> {
    private static final ArgbEvaluatorCompat instance = new ArgbEvaluatorCompat();

    public static ArgbEvaluatorCompat getInstance() {
        return instance;
    }

    public Integer evaluate(float f, Integer num, Integer num2) {
        num = num.intValue();
        float f2 = ((float) ((num >> 24) & 255)) / 255.0f;
        float f3 = ((float) ((num >> 16) & 255)) / 255.0f;
        float f4 = ((float) ((num >> 8) & 255)) / 255.0f;
        num = ((float) (num & 255)) / 1132396544;
        num2 = num2.intValue();
        float f5 = ((float) ((num2 >> 24) & 255)) / 255.0f;
        float f6 = ((float) ((num2 >> 16) & 255)) / 255.0f;
        float f7 = ((float) ((num2 >> 8) & 255)) / 255.0f;
        f3 = (float) Math.pow((double) f3, 2.2d);
        f4 = (float) Math.pow((double) f4, 2.2d);
        num = (float) Math.pow((double) num, 2.2d);
        f4 += (((float) Math.pow((double) f7, 2.2d)) - f4) * f;
        num += f * (((float) Math.pow((double) (((float) (num2 & 255)) / 1132396544), 2.2d)) - num);
        f2 = (f2 + ((f5 - f2) * f)) * 255.0f;
        return Integer.valueOf((((Math.round(((float) Math.pow((double) (f3 + ((((float) Math.pow((double) f6, 2.2d)) - f3) * f)), 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(f2) << 24)) | (Math.round(((float) Math.pow((double) f4, 0.45454545454545453d)) * 1132396544) << 8)) | Math.round(((float) Math.pow((double) num, 0.45454545454545453d)) * 1132396544));
    }
}
