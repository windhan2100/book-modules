package com.book.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
 
  
public final class NumberUtil {
    private static  Pattern p = Pattern.compile("(^\\d+$)");
    
    private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };  
    
    /** 
     * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位) 
     *  
     * @param digit  用户指定随机数据的位数 
     */  
    public static long randomLong(int digit) { 
    	try{
	        if (digit >= 19 || digit <= 0) {
	        	if (digit <= 0 ) {  
	        		digit=1;
		        }  
		        if(digit >= 19){
		        	digit=18;
		        }
	        } 
           // digit should between 1 and 18(1<=digit<=18);   
           String s = RandomStringUtils.randomNumeric(digit - 1);  
           return Long.parseLong(""+randomPrefixInt() + s);  
	    } catch (NumberFormatException e) {
			return 1;
		}
    }  
  
    /** 
     * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit 
     *  
     * @param minDigit 
     *            用户指定随机数据的最小位数 minDigit>=1 
     * @param maxDigit 
     *            用户指定随机数据的最大位数 maxDigit<=18 
     */  
    public static long randomLong(int minDigit, int maxDigit)  {  
    	try{
	        if (minDigit > maxDigit) {  
	        	minDigit = maxDigit;
	        }  
	        if (minDigit <= 0 ) {  
	        	minDigit=1;
	        }  
	        if(maxDigit >= 19){
	        	maxDigit=18;
	        }
	        return randomLong(minDigit + randomInt(maxDigit - minDigit));  
    	 } catch (NumberFormatException e) {
 			return 1;
 		 }
    }  
    /**
     * 
     * @param 1<max
     * @return
     */
    public static int randomInt(int max) {  
    	try{
        int r= RandomUtils.nextInt(max + 1);  
        if (r <= 0 ) {  
        	r=1;
        }
        return r;
    	} catch (NumberFormatException e) {
 			return 1;
 		}
    }  
  
    /** 
     * 保证第一位不是零
     */  
    public static int randomPrefixInt() {  
        return prefix[RandomUtils.nextInt(9)];  
    }  
    
	public static int getInt(String numStr, int defaultNum) {
		if (numStr == null || numStr.trim().length() == 0) {
			return defaultNum;
		}
		try {
			return Integer.valueOf(numStr);
		} catch (NumberFormatException e) {
			return defaultNum;
		}
	}
	
	public static long getLong(String numStr, long defaultNum) {
        if (numStr == null || numStr.trim().length() == 0) {
            return defaultNum;
        }
        try {
            return Long.valueOf(numStr);
        } catch (NumberFormatException e) {
            return defaultNum;
        }
    } 
	
	public static final float getFloat(String numStr, float defaultNum) {
		if (numStr == null || numStr.trim().length() == 0) {
			return defaultNum;
		}
		try {
			return Float.valueOf(numStr).floatValue();
		} catch (NumberFormatException e) {
			return defaultNum;
		}
	}
	
	public static final BigDecimal getBigDecimal(String numStr,BigDecimal defaultNum ) {
		if (numStr == null || numStr.trim().length() == 0) {
			return defaultNum;
		}
		
		return new BigDecimal(numStr);
	}
	
	public static final boolean IsNumeric(String str) {
		if (str == null || StringUtils.isBlank(str) || "null".equals(str) || str.length() == 0) {
			return false;
		}
		char[] charArr = str.toCharArray();
		char c;
		for (int i = 0; i < charArr.length; i++) {
			c = charArr[i];
			if (!Character.isDigit(c) && c != '.') {
				return false;
			}
		}
		return true;
	}
	
//--------------------------------------------------------------------
    
    /**
     * <p>Checks whether the <code>String</code> contains only
     * digit characters.</p>
     *
     * <p><code>Null</code> and empty String will return
     * <code>false</code>.</p>
     *
     * @param str  the <code>String</code> to check
     * @return <code>true</code> if str contains only unicode numeric
     */
    public static boolean isDigits(String str) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks whether the String a valid Java number.</p>
     *
     * <p>Valid numbers include hexadecimal marked with the <code>0x</code>
     * qualifier, scientific notation and numbers marked with a type
     * qualifier (e.g. 123L).</p>
     *
     * <p><code>Null</code> and empty String will return
     * <code>false</code>.</p>
     *
     * @param str  the <code>String</code> to check
     * @return <code>true</code> if the string is a correctly formatted number
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                        && (chars[i] < 'a' || chars[i] > 'f')
                        && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
              // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent   
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns
                && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                || chars[i] == 'L') {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }
	
	public static boolean getBoolean(String numStr, boolean defaultVal) {
        if (numStr == null || numStr.trim().length() == 0) {
            return defaultVal;
        }
        try {
            return Boolean.valueOf(numStr);
        } catch (Exception e) {
            return defaultVal;
        }
    }
	
	public static String getString(String numStr, String defaultVal) {
        if (numStr == null || StringUtils.isBlank(numStr) || "null".equals(numStr) ||  numStr.trim().length() == 0) {
            return defaultVal;
        }
        try {
            return numStr;
        } catch (Exception e) {
            return defaultVal;
        }
    }
	 
    public static boolean checkParamsMobileNO(String mob) {
        boolean flag = false;
        if (StringUtils.isBlank(mob) || "null".equals(mob) || mob.length() < 11) {
            return flag;
        }
        try { 
            Matcher m = p.matcher(mob);
            flag = m.find(); 
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
     
    public static String getParamsMobileNO(String mob) {
        String ph = "";
        if (StringUtils.isBlank(mob) || "null".equals(mob) || mob.length() < 11) {
            return ph;
        }
        try { 
            Matcher m = p.matcher(mob);
            boolean flag = m.find();
            if (flag) {
                ph = m.group(1);
            } 
        } catch (Exception e) {
            ph = "";
        }
        return ph;
    }

    public static String SubStringLen(String TxtData, int i, boolean is) {
        int len = 0;
        String str = "";
        try {
            len = TxtData.length();
            if (len > i) {
                if (is) {
                    str = TxtData.substring(0, i - 2) + "...";
                } else {
                    str = TxtData.substring(0, i);
                }
            } else {
                return TxtData;
            }
        } catch (Exception e) { 
        }
        return str;

    }
    
 
    public static boolean IsInteger(String TxtData) {
        TxtData = TxtData.trim();
        int vl = TxtData.length();
        for (int i = 0; i < vl; i++) {
            char c = TxtData.charAt(i);
            if (!((c >= '0' && c <= '9'))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * isBlank("")        = true
     * @param str
     * @return
     */
    public static boolean IsEmpty(String str) {
        return StringUtils.isBlank(str);
    }
 
    public static boolean IsAlpha(String TxtData) {
        TxtData = TxtData.trim();
        int vl = TxtData.length();
        for (int i = 0; i < vl; i++) {
            char c = TxtData.charAt(i);
            if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                return false;
            }
        }
        return true;
    }

 
    public static boolean IsAlNum(String TxtData) {
        TxtData = TxtData.trim();
        int vl = TxtData.length();
        for (int i = 0; i < vl; i++) {
            char c = TxtData.charAt(i);
            if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')
                    && !(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
 
    public static boolean IsFullLen(String TxtData, int MaxLen) {
        TxtData = TxtData.trim();
        int l = TxtData.length();
        if (l > 0 && l != MaxLen) {
            return false;
        }
        return true;
    }
 
    public static boolean IsDate(String TxtData) {
        TxtData = TxtData.trim(); 
        if (TxtData.length() > 0) {

            if (!IsNumeric(TxtData) || !IsFullLen(TxtData, 8)) {
                return false;
            }

            int y = new Integer(TxtData.substring(0, 4)).intValue();
            int m = new Integer(TxtData.substring(4, 6)).intValue();
            int d = new Integer(TxtData.substring(6)).intValue();

            try {
                Calendar date = new GregorianCalendar(y, m - 1, d);
                date.setLenient(false);
                date.get(Calendar.DATE);
            } catch (java.lang.IllegalArgumentException e) { 
                return false;
            }
        }
        return true;
    }

 
    public static float roundNum(float num, int dotnum) throws NumberFormatException {
        float rtnum = 0;
        try {
            StringBuffer sformat = new StringBuffer("####.0");
            for (int i = 1; i < dotnum; i++) {
                sformat.append("0");
            }
            DecimalFormat df1 = new DecimalFormat(sformat.toString());
            rtnum = Float.parseFloat(df1.format(num));
        } catch (NumberFormatException ex) { 
            ex.printStackTrace();
        }
        return rtnum;
    }
 
    public static boolean IsTime(String TxtData) {
        TxtData = TxtData.trim();

        if (TxtData.length() > 0) {
            if (!IsNumeric(TxtData) || !IsFullLen(TxtData, 6)) {
                return false;
            }
            int h = new Integer(TxtData.substring(0, 2)).intValue();
            int m = new Integer(TxtData.substring(2, 4)).intValue();
            int s = new Integer(TxtData.substring(4)).intValue();
            if (h < 0 || h > 23) {
                return false;
            }
            if (m < 0 || m > 59) {
                return false;
            }
            if (s < 0 || s > 59) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 将较大的数字格式化成单位为亿，万的字符串
     * @param num
     * @return
     */
	public static String formatLargeNum(String num){
		String res = num;
		if(num == null){
			res = "0";
		}else{
			int len = num.length();
			if(len > 8){
				res = num.substring(0, len - 8) + "." + num.charAt(len - 8) + "亿";
			} else if(len > 4){
				res = num.substring(0, len - 4) + "." + num.charAt(len - 4) + "万";
			}
		}
		return res;
	}
    
    public static void main(String[] args) {  
     
   }  
}
