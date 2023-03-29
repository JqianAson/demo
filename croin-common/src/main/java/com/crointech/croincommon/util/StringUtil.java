package com.crointech.croincommon.util;

import com.crointech.croincommon.common.BusinessException;
import com.crointech.croincommon.common.GlobalBusiness;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Roman
 * @date 2020/12/7 8:46 下午
 * @description
 **/
public final class StringUtil {

    /**
     * 校验URL可用
     *
     * @param connection 需要校验的URL
     * @return 校验结果
     */
    public static Boolean checkUrl(String connection) {
        URL url;
        try {
            url = new URL(connection);
            InputStream inputStream = url.openStream();
            if (inputStream != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 功能描述
     *
     * @param str
     * @return java.lang.Boolean
     * @author Roman
     * @date 2020/12/7
     * @desc 判空
     **/
    public final static Boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 功能描述
     *
     * @param str
     * @return java.lang.Boolean
     * @author Roman
     * @date 2020/12/7
     * @desc 判非空
     **/
    public final static Boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 邮箱格式验证正则
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z\\d]+([-_.]*[A-Za-z\\d_]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,}$");

    /**
     * 密码复杂度正则（取 至少两个不同类型的：大写字母，小写字母，数字）
     */
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$|^(?![a-z]+$)(?![0-9A-Z]+$)[0-9A-Za-z]{6,20}$|^(?![A-Z]+$)(?![0-9a-z]+$)[0-9A-Za-z]{6,20}$");

    /**
     * 5-16位 大小写数字  3选2
     */
    private static final Pattern PASSWORD_PATTERN_2 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,16}$");

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    /**
     * 字符串类型__手机号
     */
    private static final int TELEPHONE_TYPE = 1;
    /**
     * 字符串类型__邮箱
     */
    private static final int EMAIL_TYPE = 2;
    /**
     * 字符串类型__身份证号
     */
    public static final int ID_NUMBER_TYPE = 3;
    /**
     * 银行卡号
     */
    public static final int BANKCARD_NUMBER_TYPE = 4;
    /**
     * 电汇 iban
     */
    public static final int IBAN_NUMBER_TYPE = 5;
    /**
     * 手机号长度参数
     */
    private static final int TELEPHONE_LENGTH = 6;

    /**
     * 密码基本校验
     */
    public static String pass = "^(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).{6,20}$";

    /**
     * 中(密码长度8-20位且必需包含大小写字母和数字中的两种)
     */
    private static String passLevel2 = "^(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).{8,20}$";

    /**
     * 强(密码长度8-20位且必需包含大小写字母和数字且长度>8)
     */
    private static String passLevel3 = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[0-9])(?=.*([0-9]|[a-z]|[A-Z])).{8,20}$";

    /**
     * 创建UUID
     *
     * @return
     */
    public final static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断 a是否等于b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Object a, Object b) {
        if ((a == null) && (b == null)) {
            return false;
        }
        if (a == null) {
            return b.equals(a);
        } else {
            return a.equals(b);
        }
    }

    /**
     * 判断 a是否等于b
     *
     * @param a
     * @param b
     * @param defaultIs 如果a b 皆为null 返回
     * @return
     */
    public static boolean equals(Object a, Object b, boolean defaultIs) {
        if ((a == null) && (b == null)) {
            return defaultIs;
        }
        if (a == null) {
            return b.equals(a);
        } else {
            return a.equals(b);
        }
    }


    /**
     * 判断字符串是否为空或空白字符串
     *
     * @param str
     * @return
     */
    public final static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 去空处理
     *
     * @param str
     * @return
     */
    public final static String goEmpty(String str) {
        if (isBlank(str)) {
            return "";
        }
        return str.replace(" ", "");
    }


    /**
     * 替换邮件模版变量
     *
     * @param html      发送模版
     * @param parameter 变量
     * @return 替换结果
     */
    public static String replaceHtml(String html, Map<String, Object> parameter) {
        if (null == html) {
            throw new BusinessException(GlobalBusiness.PARAM_LOSS);
        }

        //传递变量就替换，不传则返回模版本身
        if (!CollectionUtils.isEmpty(parameter)) {
            for (Map.Entry<String, Object> entry : parameter.entrySet()) {
                if (html.contains(entry.getKey())) {
                    html = html.replace("${" + entry.getKey() + "}", entry.getValue().toString());
                }
            }
        }
        return html;
    }

    /**
     * 校验邮箱格式
     *
     * @return
     */
    public static boolean patternMail(String mail) {
        Matcher matcher = EMAIL_PATTERN.matcher(mail);
        return matcher.matches();
    }

    /**
     * 密码复杂度, 必需包含大小写和数字
     *
     * @return
     */
    public static boolean patternPassword(String originPassword) {
        Matcher matcher = PASSWORD_PATTERN.matcher(originPassword);
        return matcher.matches();
    }

    /**
     * 密码复杂度2, 必需包含大小写和数字
     *
     * @return
     */
    public static boolean patternPassword2(String originPassword) {
        Matcher matcher = PASSWORD_PATTERN_2.matcher(originPassword);
        return matcher.matches();
    }

    /**
     * 数字校验
     *
     * @param originNumber 数字
     * @return boolean
     */
    public static boolean patternNumber(String originNumber) {
        Matcher matcher = NUMBER_PATTERN.matcher(originNumber);
        return matcher.matches();
    }

    /**
     * 将一个字符串split为List，该List支出remove操作
     *
     * @param srcStr
     * @param regex
     * @return
     */
    public final List<String> splitToList(String srcStr, String regex) {
        if (isBlank(srcStr)) {
            return new ArrayList<>(0);
        }
        String[] splitStrs = srcStr.split(regex);
        List<String> list = new ArrayList<>(splitStrs.length * 3);
        for (String splitStr : splitStrs) {
            list.add(splitStr);
        }
        return list;
    }

    /**
     * 处理邮箱和手机号
     *
     * @param str  参数
     * @param type 1:手机号   2：邮箱   3: 身份证号   4 银行卡号
     * @return 隐藏部分值
     */
    public static String hideString(String str, int type) {
        if (isBlank(str)) {
            return str;
        }
        if (type == TELEPHONE_TYPE) {

            str = str.replaceAll(" ", "");
            String pre = "";
            if (str.startsWith("+")) {
                pre = "+";
                str = str.replaceAll("\\+", "");
            }
            int length = str.length();
            if (length >= TELEPHONE_LENGTH) {
                String start = str.substring(0, 3);
                String end = str.substring(length - 4, length);
                return pre + start + " **** " + end;
//                int middle = length - TELEPHONE_LENGTH;
//                String f = "(\\d{3})\\d";
//                String m = "{"+ middle;
//                String l = "}(\\d{3})";
//                return pre + str.replaceAll(f+m+l, "$1****$2");
            }
        } else if (type == EMAIL_TYPE) {
            str = str.replaceAll(" ", "");
            String[] arr = str.split("@");
            if (arr.length > 1) {
                String s = arr[0];
                if (s.length() > 1) {
                    String[] sub = s.split("");
                    return sub[0] + "****@" + arr[1];
                }
            } else {
                return str;
            }
//            return str.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[A-z]+(\\.[A-z]+)?)", "$1****$3$4");
        } else if (type == ID_NUMBER_TYPE) {
            if (isEmpty(str) || (str.length() < 8)) {
                return str;
            }
            return str.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
        } else if (type == BANKCARD_NUMBER_TYPE) {
            if (isEmpty(str) || (str.length() < 8)) {
                return str;
            }
            return str.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
        } else if (type == IBAN_NUMBER_TYPE) {
            if (isEmpty(str) || (str.length() < 6)) {
                return str;
            }
            return str.replaceAll("(?<=\\w{2})\\w(?=\\w{3})", "*");
        }
        return str;
    }

    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] == '-' ? 1 : 0;
            int i;
            if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while (i < chars.length) {
                        if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for (i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (chars[i] == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (chars[i] != 'e' && chars[i] != 'E') {
                        if (chars[i] != '+' && chars[i] != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < chars.length) {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        return true;
                    } else if (chars[i] != 'e' && chars[i] != 'E') {
                        if (chars[i] == '.') {
                            return !hasDecPoint && !hasExp ? foundDigit : false;
                        } else if (allowSigns || chars[i] != 'd' && chars[i] != 'D' && chars[i] != 'f' && chars[i] != 'F') {
                            if (chars[i] != 'l' && chars[i] != 'L') {
                                return false;
                            } else {
                                return foundDigit && !hasExp;
                            }
                        } else {
                            return foundDigit;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }


    public static byte[] hexString2Bytes(String src) {
        int length = src.length() / 2;
        byte[] result = new byte[length];
        char[] array = src.toCharArray();
        for (int i = 0; i < length; i++) {
            result[i] = uniteBytes(array[2 * i], array[2 * i + 1]);
        }
        return result;
    }

    public static String byte2HexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static byte uniteBytes(char mostChar, char secondChar) {
        byte b0 = Byte.decode("0x" + String.valueOf(mostChar));
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + String.valueOf(secondChar));
        return (byte) (b0 | b1);
    }

    public static String join(Object[] array, String delimiter) {
        List<Object> list;
        if (array == null || array.length == 0) {
            list = Collections.emptyList();
        } else {
            list = Arrays.asList(array);
        }
        return join(list, delimiter);
    }

    public static String join(List<?> list, String delimiter) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        if (delimiter == null || delimiter.isEmpty()) {
            delimiter = " ";
        }
        StringBuilder buffer = new StringBuilder();
        int count = 1;
        for (Object aList : list) {
            if (aList == null) {
                continue;
            }
            if (count != 1) {
                buffer.append(delimiter);
            }
            buffer.append(aList.toString());
            count++;
        }
        return buffer.toString();
    }

    /**
     * 密码级别：1-弱，2-中，3-强
     *
     * @param password 密码
     * @return Integer
     */
    public static Integer passwordLevel(String password) {
        if (Pattern.matches(passLevel3, password)) {
            return 3;
        } else if (Pattern.matches(passLevel2, password)) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * 从html中提取纯文本
     *
     * @param strHtml
     * @return
     */
    public static String StripHT(String strHtml) {
        if (!isBlank(strHtml)) {
            //剔出<html>的标签
            String txtcontent = strHtml.replaceAll("</?[^>]+>", "");
            //去除字符串中的空格,回车,换行符,制表符 (英文的文本不友好)
            //txtcontent = txtcontent.replaceAll("\\s*|\t|\r|\n", "");
            return txtcontent;//.replace("　", "");
        }
        return "";
    }

    /**
     * 获取随机生成的验证码
     */
    public static String getVerifyCode(int len) {
        String[] verifyString = new String[]{"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9"};
        Random random = new Random(System.currentTimeMillis());
        StringBuilder verifyBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int rd = random.nextInt(10);
            verifyBuilder.append(verifyString[rd]);
        }
        return verifyBuilder.toString();
    }

    public static String generateRandom() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static String generateRandomDemoAccountPassword() {
        String uuid = UUID.randomUUID().toString();
        return "A" + new Random().nextInt(100) + "a" + uuid.substring(0, 4);
    }
}
