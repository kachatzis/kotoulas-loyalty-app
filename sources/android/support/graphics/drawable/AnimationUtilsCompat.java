package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context context, int i) throws NotFoundException {
        StringBuilder stringBuilder;
        NotFoundException notFoundException;
        if (VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, i);
        }
        XmlResourceParser xmlResourceParser = null;
        if (i == AndroidResources.FAST_OUT_LINEAR_IN) {
            try {
                return new FastOutLinearInInterpolator();
            } catch (Context context2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Can't load animation resource ID #0x");
                stringBuilder.append(Integer.toHexString(i));
                notFoundException = new NotFoundException(stringBuilder.toString());
                notFoundException.initCause(context2);
                throw notFoundException;
            } catch (Context context22) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Can't load animation resource ID #0x");
                stringBuilder.append(Integer.toHexString(i));
                notFoundException = new NotFoundException(stringBuilder.toString());
                notFoundException.initCause(context22);
                throw notFoundException;
            } catch (Throwable th) {
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            }
        } else if (i == AndroidResources.FAST_OUT_SLOW_IN) {
            return new FastOutSlowInInterpolator();
        } else {
            if (i == AndroidResources.LINEAR_OUT_SLOW_IN) {
                return new LinearOutSlowInInterpolator();
            }
            Object animation = context22.getResources().getAnimation(i);
            context22 = createInterpolatorFromXml(context22, context22.getResources(), context22.getTheme(), animation);
            if (animation != null) {
                animation.close();
            }
            return context22;
        }
    }

    private static Interpolator createInterpolatorFromXml(Context context, Resources resources, Theme theme, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        resources = xmlPullParser.getDepth();
        theme = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > resources) && next != 1) {
                if (next == 2) {
                    theme = Xml.asAttributeSet(xmlPullParser);
                    String name = xmlPullParser.getName();
                    if (name.equals("linearInterpolator")) {
                        theme = new LinearInterpolator();
                    } else if (name.equals("accelerateInterpolator")) {
                        theme = new AccelerateInterpolator(context, theme);
                    } else if (name.equals("decelerateInterpolator")) {
                        theme = new DecelerateInterpolator(context, theme);
                    } else if (name.equals("accelerateDecelerateInterpolator")) {
                        theme = new AccelerateDecelerateInterpolator();
                    } else if (name.equals("cycleInterpolator")) {
                        theme = new CycleInterpolator(context, theme);
                    } else if (name.equals("anticipateInterpolator")) {
                        theme = new AnticipateInterpolator(context, theme);
                    } else if (name.equals("overshootInterpolator")) {
                        theme = new OvershootInterpolator(context, theme);
                    } else if (name.equals("anticipateOvershootInterpolator")) {
                        theme = new AnticipateOvershootInterpolator(context, theme);
                    } else if (name.equals("bounceInterpolator")) {
                        theme = new BounceInterpolator();
                    } else if (name.equals("pathInterpolator")) {
                        theme = new PathInterpolatorCompat(context, theme, xmlPullParser);
                    } else {
                        resources = new StringBuilder();
                        resources.append("Unknown interpolator name: ");
                        resources.append(xmlPullParser.getName());
                        throw new RuntimeException(resources.toString());
                    }
                }
            }
        }
        return theme;
    }

    private AnimationUtilsCompat() {
    }
}
