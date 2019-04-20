package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.UiThread;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Preconditions;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PrecomputedTextCompat implements Spannable {
    private static final char LINE_FEED = '\n';
    @GuardedBy("sLock")
    @NonNull
    private static Executor sExecutor = null;
    private static final Object sLock = new Object();
    @NonNull
    private final int[] mParagraphEnds;
    @NonNull
    private final Params mParams;
    @NonNull
    private final Spannable mText;
    @Nullable
    private final PrecomputedText mWrapped;

    public static final class Params {
        private final int mBreakStrategy;
        private final int mHyphenationFrequency;
        @NonNull
        private final TextPaint mPaint;
        @Nullable
        private final TextDirectionHeuristic mTextDir;
        final android.text.PrecomputedText.Params mWrapped;

        public static class Builder {
            private int mBreakStrategy;
            private int mHyphenationFrequency;
            @NonNull
            private final TextPaint mPaint;
            private TextDirectionHeuristic mTextDir;

            public Builder(@NonNull TextPaint textPaint) {
                this.mPaint = textPaint;
                if (VERSION.SDK_INT >= 23) {
                    this.mBreakStrategy = 1;
                    this.mHyphenationFrequency = 1;
                } else {
                    this.mHyphenationFrequency = 0;
                    this.mBreakStrategy = 0;
                }
                if (VERSION.SDK_INT >= 18) {
                    this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.mTextDir = null;
                }
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int i) {
                this.mBreakStrategy = i;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int i) {
                this.mHyphenationFrequency = i;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
                this.mTextDir = textDirectionHeuristic;
                return this;
            }

            @NonNull
            public Params build() {
                return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            }
        }

        Params(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (VERSION.SDK_INT >= 28) {
                this.mWrapped = new android.text.PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.mWrapped = null;
            }
            this.mPaint = textPaint;
            this.mTextDir = textDirectionHeuristic;
            this.mBreakStrategy = i;
            this.mHyphenationFrequency = i2;
        }

        @RequiresApi(28)
        public Params(@NonNull android.text.PrecomputedText.Params params) {
            this.mPaint = params.getTextPaint();
            this.mTextDir = params.getTextDirection();
            this.mBreakStrategy = params.getBreakStrategy();
            this.mHyphenationFrequency = params.getHyphenationFrequency();
            this.mWrapped = params;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null) {
                if (obj instanceof Params) {
                    Params params = (Params) obj;
                    android.text.PrecomputedText.Params params2 = this.mWrapped;
                    if (params2 != null) {
                        return params2.equals(params.mWrapped);
                    }
                    if (VERSION.SDK_INT >= 23 && (this.mBreakStrategy != params.getBreakStrategy() || this.mHyphenationFrequency != params.getHyphenationFrequency())) {
                        return false;
                    }
                    if ((VERSION.SDK_INT >= 18 && this.mTextDir != params.getTextDirection()) || this.mPaint.getTextSize() != params.getTextPaint().getTextSize() || this.mPaint.getTextScaleX() != params.getTextPaint().getTextScaleX() || this.mPaint.getTextSkewX() != params.getTextPaint().getTextSkewX()) {
                        return false;
                    }
                    if ((VERSION.SDK_INT >= 21 && (this.mPaint.getLetterSpacing() != params.getTextPaint().getLetterSpacing() || !TextUtils.equals(this.mPaint.getFontFeatureSettings(), params.getTextPaint().getFontFeatureSettings()))) || this.mPaint.getFlags() != params.getTextPaint().getFlags()) {
                        return false;
                    }
                    if (VERSION.SDK_INT >= 24) {
                        if (!this.mPaint.getTextLocales().equals(params.getTextPaint().getTextLocales())) {
                            return false;
                        }
                    } else if (VERSION.SDK_INT >= 17 && !this.mPaint.getTextLocale().equals(params.getTextPaint().getTextLocale())) {
                        return false;
                    }
                    if (this.mPaint.getTypeface() == null) {
                        if (params.getTextPaint().getTypeface() != null) {
                            return false;
                        }
                    } else if (this.mPaint.getTypeface().equals(params.getTextPaint().getTypeface()) == null) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (VERSION.SDK_INT >= 24) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else if (VERSION.SDK_INT >= 21) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else if (VERSION.SDK_INT >= 18) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else if (VERSION.SDK_INT >= 17) {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            } else {
                return ObjectsCompat.hash(Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency));
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("{");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("textSize=");
            stringBuilder2.append(this.mPaint.getTextSize());
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", textScaleX=");
            stringBuilder2.append(this.mPaint.getTextScaleX());
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", textSkewX=");
            stringBuilder2.append(this.mPaint.getTextSkewX());
            stringBuilder.append(stringBuilder2.toString());
            if (VERSION.SDK_INT >= 21) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", letterSpacing=");
                stringBuilder2.append(this.mPaint.getLetterSpacing());
                stringBuilder.append(stringBuilder2.toString());
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", elegantTextHeight=");
                stringBuilder2.append(this.mPaint.isElegantTextHeight());
                stringBuilder.append(stringBuilder2.toString());
            }
            if (VERSION.SDK_INT >= 24) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", textLocale=");
                stringBuilder2.append(this.mPaint.getTextLocales());
                stringBuilder.append(stringBuilder2.toString());
            } else if (VERSION.SDK_INT >= 17) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", textLocale=");
                stringBuilder2.append(this.mPaint.getTextLocale());
                stringBuilder.append(stringBuilder2.toString());
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", typeface=");
            stringBuilder2.append(this.mPaint.getTypeface());
            stringBuilder.append(stringBuilder2.toString());
            if (VERSION.SDK_INT >= 26) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", variationSettings=");
                stringBuilder2.append(this.mPaint.getFontVariationSettings());
                stringBuilder.append(stringBuilder2.toString());
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", textDir=");
            stringBuilder2.append(this.mTextDir);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", breakStrategy=");
            stringBuilder2.append(this.mBreakStrategy);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(", hyphenationFrequency=");
            stringBuilder2.append(this.mHyphenationFrequency);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {

        private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
            private Params mParams;
            private CharSequence mText;

            PrecomputedTextCallback(@NonNull Params params, @NonNull CharSequence charSequence) {
                this.mParams = params;
                this.mText = charSequence;
            }

            public PrecomputedTextCompat call() throws Exception {
                return PrecomputedTextCompat.create(this.mText, this.mParams);
            }
        }

        PrecomputedTextFutureTask(@NonNull Params params, @NonNull CharSequence charSequence) {
            super(new PrecomputedTextCallback(params, charSequence));
        }
    }

    public static android.support.v4.text.PrecomputedTextCompat create(@android.support.annotation.NonNull java.lang.CharSequence r11, @android.support.annotation.NonNull android.support.v4.text.PrecomputedTextCompat.Params r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:33:0x00b9 in {8, 14, 15, 16, 20, 23, 26, 29, 32} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        android.support.v4.util.Preconditions.checkNotNull(r11);
        android.support.v4.util.Preconditions.checkNotNull(r12);
        r0 = "PrecomputedText";	 Catch:{ all -> 0x00b4 }
        android.support.v4.os.TraceCompat.beginSection(r0);	 Catch:{ all -> 0x00b4 }
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x00b4 }
        r1 = 28;	 Catch:{ all -> 0x00b4 }
        if (r0 < r1) goto L_0x0024;	 Catch:{ all -> 0x00b4 }
    L_0x0011:
        r0 = r12.mWrapped;	 Catch:{ all -> 0x00b4 }
        if (r0 == 0) goto L_0x0024;	 Catch:{ all -> 0x00b4 }
    L_0x0015:
        r0 = new android.support.v4.text.PrecomputedTextCompat;	 Catch:{ all -> 0x00b4 }
        r1 = r12.mWrapped;	 Catch:{ all -> 0x00b4 }
        r11 = android.text.PrecomputedText.create(r11, r1);	 Catch:{ all -> 0x00b4 }
        r0.<init>(r11, r12);	 Catch:{ all -> 0x00b4 }
        android.support.v4.os.TraceCompat.endSection();
        return r0;
    L_0x0024:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x00b4 }
        r0.<init>();	 Catch:{ all -> 0x00b4 }
        r1 = r11.length();	 Catch:{ all -> 0x00b4 }
        r2 = 0;	 Catch:{ all -> 0x00b4 }
        r3 = 0;	 Catch:{ all -> 0x00b4 }
    L_0x002f:
        if (r3 >= r1) goto L_0x0045;	 Catch:{ all -> 0x00b4 }
    L_0x0031:
        r4 = 10;	 Catch:{ all -> 0x00b4 }
        r3 = android.text.TextUtils.indexOf(r11, r4, r3, r1);	 Catch:{ all -> 0x00b4 }
        if (r3 >= 0) goto L_0x003b;	 Catch:{ all -> 0x00b4 }
    L_0x0039:
        r3 = r1;	 Catch:{ all -> 0x00b4 }
        goto L_0x003d;	 Catch:{ all -> 0x00b4 }
    L_0x003b:
        r3 = r3 + 1;	 Catch:{ all -> 0x00b4 }
    L_0x003d:
        r4 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x00b4 }
        r0.add(r4);	 Catch:{ all -> 0x00b4 }
        goto L_0x002f;	 Catch:{ all -> 0x00b4 }
    L_0x0045:
        r1 = r0.size();	 Catch:{ all -> 0x00b4 }
        r1 = new int[r1];	 Catch:{ all -> 0x00b4 }
        r3 = 0;	 Catch:{ all -> 0x00b4 }
    L_0x004c:
        r4 = r0.size();	 Catch:{ all -> 0x00b4 }
        if (r3 >= r4) goto L_0x0061;	 Catch:{ all -> 0x00b4 }
    L_0x0052:
        r4 = r0.get(r3);	 Catch:{ all -> 0x00b4 }
        r4 = (java.lang.Integer) r4;	 Catch:{ all -> 0x00b4 }
        r4 = r4.intValue();	 Catch:{ all -> 0x00b4 }
        r1[r3] = r4;	 Catch:{ all -> 0x00b4 }
        r3 = r3 + 1;	 Catch:{ all -> 0x00b4 }
        goto L_0x004c;	 Catch:{ all -> 0x00b4 }
    L_0x0061:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x00b4 }
        r3 = 23;	 Catch:{ all -> 0x00b4 }
        if (r0 < r3) goto L_0x0092;	 Catch:{ all -> 0x00b4 }
    L_0x0067:
        r0 = r11.length();	 Catch:{ all -> 0x00b4 }
        r3 = r12.getTextPaint();	 Catch:{ all -> 0x00b4 }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;	 Catch:{ all -> 0x00b4 }
        r0 = android.text.StaticLayout.Builder.obtain(r11, r2, r0, r3, r4);	 Catch:{ all -> 0x00b4 }
        r2 = r12.getBreakStrategy();	 Catch:{ all -> 0x00b4 }
        r0 = r0.setBreakStrategy(r2);	 Catch:{ all -> 0x00b4 }
        r2 = r12.getHyphenationFrequency();	 Catch:{ all -> 0x00b4 }
        r0 = r0.setHyphenationFrequency(r2);	 Catch:{ all -> 0x00b4 }
        r2 = r12.getTextDirection();	 Catch:{ all -> 0x00b4 }
        r0 = r0.setTextDirection(r2);	 Catch:{ all -> 0x00b4 }
        r0.build();	 Catch:{ all -> 0x00b4 }
        goto L_0x00ab;	 Catch:{ all -> 0x00b4 }
    L_0x0092:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x00b4 }
        r2 = 21;	 Catch:{ all -> 0x00b4 }
        if (r0 < r2) goto L_0x00ab;	 Catch:{ all -> 0x00b4 }
    L_0x0098:
        r3 = new android.text.StaticLayout;	 Catch:{ all -> 0x00b4 }
        r5 = r12.getTextPaint();	 Catch:{ all -> 0x00b4 }
        r6 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;	 Catch:{ all -> 0x00b4 }
        r7 = android.text.Layout.Alignment.ALIGN_NORMAL;	 Catch:{ all -> 0x00b4 }
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;	 Catch:{ all -> 0x00b4 }
        r9 = 0;	 Catch:{ all -> 0x00b4 }
        r10 = 0;	 Catch:{ all -> 0x00b4 }
        r4 = r11;	 Catch:{ all -> 0x00b4 }
        r3.<init>(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x00b4 }
    L_0x00ab:
        r0 = new android.support.v4.text.PrecomputedTextCompat;	 Catch:{ all -> 0x00b4 }
        r0.<init>(r11, r12, r1);	 Catch:{ all -> 0x00b4 }
        android.support.v4.os.TraceCompat.endSection();
        return r0;
    L_0x00b4:
        r11 = move-exception;
        android.support.v4.os.TraceCompat.endSection();
        throw r11;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.text.PrecomputedTextCompat.create(java.lang.CharSequence, android.support.v4.text.PrecomputedTextCompat$Params):android.support.v4.text.PrecomputedTextCompat");
    }

    private int findParaIndex(@android.support.annotation.IntRange(from = 0) int r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:9:0x0034 in {5, 6, 8} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = 0;
    L_0x0001:
        r1 = r4.mParagraphEnds;
        r2 = r1.length;
        if (r0 >= r2) goto L_0x000e;
    L_0x0006:
        r1 = r1[r0];
        if (r5 >= r1) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x000e:
        r0 = new java.lang.IndexOutOfBoundsException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "pos must be less than ";
        r1.append(r2);
        r2 = r4.mParagraphEnds;
        r3 = r2.length;
        r3 = r3 + -1;
        r2 = r2[r3];
        r1.append(r2);
        r2 = ", gave ";
        r1.append(r2);
        r1.append(r5);
        r5 = r1.toString();
        r0.<init>(r5);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.text.PrecomputedTextCompat.findParaIndex(int):int");
    }

    private PrecomputedTextCompat(@NonNull CharSequence charSequence, @NonNull Params params, @NonNull int[] iArr) {
        this.mText = new SpannableString(charSequence);
        this.mParams = params;
        this.mParagraphEnds = iArr;
        this.mWrapped = null;
    }

    @RequiresApi(28)
    private PrecomputedTextCompat(@NonNull PrecomputedText precomputedText, @NonNull Params params) {
        this.mText = precomputedText;
        this.mParams = params;
        this.mParagraphEnds = null;
        this.mWrapped = precomputedText;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public PrecomputedText getPrecomputedText() {
        Spannable spannable = this.mText;
        return spannable instanceof PrecomputedText ? (PrecomputedText) spannable : null;
    }

    @NonNull
    public Params getParams() {
        return this.mParams;
    }

    @IntRange(from = 0)
    public int getParagraphCount() {
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphCount();
        }
        return this.mParagraphEnds.length;
    }

    @IntRange(from = 0)
    public int getParagraphStart(@IntRange(from = 0) int i) {
        int i2 = 0;
        Preconditions.checkArgumentInRange(i, 0, getParagraphCount(), "paraIndex");
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphStart(i);
        }
        if (i != 0) {
            i2 = this.mParagraphEnds[i - 1];
        }
        return i2;
    }

    @IntRange(from = 0)
    public int getParagraphEnd(@IntRange(from = 0) int i) {
        Preconditions.checkArgumentInRange(i, 0, getParagraphCount(), "paraIndex");
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphEnd(i);
        }
        return this.mParagraphEnds[i];
    }

    @UiThread
    public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence charSequence, @NonNull Params params, @Nullable Executor executor) {
        Object precomputedTextFutureTask = new PrecomputedTextFutureTask(params, charSequence);
        if (executor == null) {
            synchronized (sLock) {
                if (sExecutor == null) {
                    sExecutor = Executors.newFixedThreadPool(1);
                }
                executor = sExecutor;
            }
        }
        executor.execute(precomputedTextFutureTask);
        return precomputedTextFutureTask;
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        } else if (VERSION.SDK_INT >= 28) {
            this.mWrapped.setSpan(obj, i, i2, i3);
        } else {
            this.mText.setSpan(obj, i, i2, i3);
        }
    }

    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        } else if (VERSION.SDK_INT >= 28) {
            this.mWrapped.removeSpan(obj);
        } else {
            this.mText.removeSpan(obj);
        }
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        if (VERSION.SDK_INT >= 28) {
            return this.mWrapped.getSpans(i, i2, cls);
        }
        return this.mText.getSpans(i, i2, cls);
    }

    public int getSpanStart(Object obj) {
        return this.mText.getSpanStart(obj);
    }

    public int getSpanEnd(Object obj) {
        return this.mText.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.mText.getSpanFlags(obj);
    }

    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.mText.nextSpanTransition(i, i2, cls);
    }

    public int length() {
        return this.mText.length();
    }

    public char charAt(int i) {
        return this.mText.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.mText.subSequence(i, i2);
    }

    public String toString() {
        return this.mText.toString();
    }
}
