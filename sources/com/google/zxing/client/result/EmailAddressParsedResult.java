package com.google.zxing.client.result;

public final class EmailAddressParsedResult extends ParsedResult {
    private final String[] bccs;
    private final String body;
    private final String[] ccs;
    private final String subject;
    private final String[] tos;

    @Deprecated
    public String getMailtoURI() {
        return "mailto:";
    }

    EmailAddressParsedResult(String str) {
        this(new String[]{str}, null, null, null, null);
    }

    EmailAddressParsedResult(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2) {
        super(ParsedResultType.EMAIL_ADDRESS);
        this.tos = strArr;
        this.ccs = strArr2;
        this.bccs = strArr3;
        this.subject = str;
        this.body = str2;
    }

    @Deprecated
    public String getEmailAddress() {
        String[] strArr = this.tos;
        if (strArr != null) {
            if (strArr.length != 0) {
                return strArr[0];
            }
        }
        return null;
    }

    public String[] getTos() {
        return this.tos;
    }

    public String[] getCCs() {
        return this.ccs;
    }

    public String[] getBCCs() {
        return this.bccs;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }

    public String getDisplayResult() {
        StringBuilder stringBuilder = new StringBuilder(30);
        ParsedResult.maybeAppend(this.tos, stringBuilder);
        ParsedResult.maybeAppend(this.ccs, stringBuilder);
        ParsedResult.maybeAppend(this.bccs, stringBuilder);
        ParsedResult.maybeAppend(this.subject, stringBuilder);
        ParsedResult.maybeAppend(this.body, stringBuilder);
        return stringBuilder.toString();
    }
}
