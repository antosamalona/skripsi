/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.view.component;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

public class AlphaNumericUtility {

    private Locale locale;
    
    public String[] indexHurufTerbilang = {
        "", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan",
        "Sembilan", "Sepuluh", "Sebelas"
    };

    public AlphaNumericUtility() {
        this.locale = new Locale("Indonesian", "Indonesia");
    }

    public String toRupiah(double value) {
        DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols(this.locale);
        formatRp.setCurrencySymbol("Rp.");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kurs.setDecimalFormatSymbols(formatRp);
        return kurs.format(value);
    }

    public String formatRp(String value) {
        String string = parseStringDotComma(value), result = "";
        int l = string.length();
        String[] res = null;
        if (l % 3 == 0) {
            res = new String[l / 3];
            for (int i = 0; i <= l; i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 0; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 0) {
                                res[j] = string.substring(i - i, i);
                            } else {
                                res[j] = string.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        } else if (l % 3 == 1) {
            res = new String[(l / 3) + 1];
            res[0] = string.substring(0, 1);
            String partStr = string.substring(1, l);
            for (int i = 0; i <= partStr.length(); i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 1; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 1) {
                                res[j] = partStr.substring(i - i, i);
                            } else {
                                res[j] = partStr.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        } else if (l % 3 == 2) {
            res = new String[(l / 3) + 1];
            res[0] = string.substring(0, 2);
            String partStr = string.substring(2, l);
            for (int i = 0; i <= partStr.length(); i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 1; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 1) {
                                res[j] = partStr.substring(i - i, i);
                            } else {
                                res[j] = partStr.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (i == res.length - 1) {
                result = result + res[i];
            } else {
                result = result + res[i] + ".";
            }
        }
        return "Rp. " + result + ",00";
    }

    public String formatRp(double value) {
        String strVal = String.valueOf(new Double(value).intValue());
        String string = parseStringDotComma(strVal), result = "";
        int l = string.length();
        String[] res = null;
        if (l % 3 == 0) {
            res = new String[l / 3];
            for (int i = 0; i <= l; i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 0; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 0) {
                                res[j] = string.substring(i - i, i);
                            } else {
                                res[j] = string.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        } else if (l % 3 == 1) {
            res = new String[(l / 3) + 1];
            res[0] = string.substring(0, 1);
            String partStr = string.substring(1, l);
            for (int i = 0; i <= partStr.length(); i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 1; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 1) {
                                res[j] = partStr.substring(i - i, i);
                            } else {
                                res[j] = partStr.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        } else if (l % 3 == 2) {
            res = new String[(l / 3) + 1];
            res[0] = string.substring(0, 2);
            String partStr = string.substring(2, l);
            for (int i = 0; i <= partStr.length(); i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 1; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 1) {
                                res[j] = partStr.substring(i - i, i);
                            } else {
                                res[j] = partStr.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (i == res.length - 1) {
                result = result + res[i];
            } else {
                result = result + res[i] + ".";
            }
        }
        return "Rp. " + result + ",00";
    }

    public String formatRp(long value) {
        String strVal = String.valueOf(new Long(value));
        String string = parseStringDotComma(strVal), result = "";
        int l = string.length();
        String[] res = null;
        if (l % 3 == 0) {
            res = new String[l / 3];
            for (int i = 0; i <= l; i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 0; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 0) {
                                res[j] = string.substring(i - i, i);
                            } else {
                                res[j] = string.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        } else if (l % 3 == 1) {
            res = new String[(l / 3) + 1];
            res[0] = string.substring(0, 1);
            String partStr = string.substring(1, l);
            for (int i = 0; i <= partStr.length(); i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 1; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 1) {
                                res[j] = partStr.substring(i - i, i);
                            } else {
                                res[j] = partStr.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        } else if (l % 3 == 2) {
            res = new String[(l / 3) + 1];
            res[0] = string.substring(0, 2);
            String partStr = string.substring(2, l);
            for (int i = 0; i <= partStr.length(); i++) {
                if (i % 3 == 0 && i != 0) {
                    for (int j = 1; j < res.length; j++) {
                        if (res[j] != null) {
                            continue;
                        } else {
                            if (j == 1) {
                                res[j] = partStr.substring(i - i, i);
                            } else {
                                res[j] = partStr.substring(i - 3, i);
                            }
                            break;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (i == res.length - 1) {
                result = result + res[i];
            } else {
                result = result + res[i] + ".";
            }
        }
        return "Rp. " + result + ",00";
    }

    public double extractRpDouble(String value) {
        double result = 0;
        String ex = "";
        if (value.contains("Rp. ")) {
            ex = value.split("Rp. ")[1];
        } else if (value.contains("Rp.")) {
            ex = value.split("Rp.")[1];
        }
        String temp = this.parseStringDotComma(ex);
        result = Double.parseDouble(temp);
        return result;
    }

    public long extractRpLong(String value) {
        long result = 0;
        String ex = "";
        if (value.contains("Rp. ")) {
            ex = value.split("Rp. ")[1];
        } else if (value.contains("Rp.")) {
            ex = value.split("Rp.")[1];
        }else{
            ex = value;
        }
        String temp = this.parseStringDotComma(ex);
        result = Long.parseLong(temp);
        return result;
    }
    
    public int extractRpInteger(String value) {
        Integer result = 0;
        String ex = "";
        if (value.contains("Rp. ")) {
            ex = value.split("Rp. ")[1];
        } else if (value.contains("Rp.")) {
            ex = value.split("Rp.")[1];
        }else{
            ex = value;
        }
        String temp = this.parseStringDotComma(ex);
        result = Integer.parseInt(temp);
        return result.intValue();
    }

    public String extractRpString(String value) {
        String ex = "";
        if (value.contains("Rp. ")) {
            ex = value.split("Rp. ")[1];
        } else if (value.contains("Rp.")) {
            ex = value.split("Rp.")[1];
        }
        String temp = this.parseStringDotComma(ex);
        return temp;
    }

    public String unformat(JFormattedTextField text) {
        text.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        return text.getValue().toString();
    }

    public Double toDouble(String value) {
        double result = 0;
        try {
            result = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Format Angka Salah!");
            ex.printStackTrace();
        }
        return result;
    }

    public Double parseDotComma(String value) {
        //System.out.println(value);
        int v = new Double(value).intValue();
        //System.out.println(v);
        String s = String.valueOf(v);
        //System.out.println(s);
        double result = 0;
        StringBuilder newstr = new StringBuilder(s.split("\\,", 2)[0]);
        for (int i = 0; i < newstr.length(); i++) {
            if (newstr.charAt(i) == '.') {
                newstr.deleteCharAt(i);
            }
        }
        result = toDouble(newstr.toString());
        return result;
    }

    public String parseStringDotComma(String value) {
        StringBuilder newstr = new StringBuilder(value.split("\\,", 2)[0]);
        for (int i = 0; i < newstr.length(); i++) {
            if (newstr.charAt(i) == '.') {
                newstr.deleteCharAt(i);
            }
        }
        return newstr.toString();
    }

    public String randomString(int length) {
        char[] characterSet = "0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }

    public String randomNumericString(int length) {
        char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }

    public String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) extension = fileName.substring(i + 1);
        
        return extension;
    }
    
    public String getTerbilang(Long angka) {
        if(angka < 12) return indexHurufTerbilang[angka.intValue()];
        if(angka >= 12 && angka <= 19) return indexHurufTerbilang[angka.intValue() % 10] + " Belas";
        if(angka >= 20 && angka <= 99) return getTerbilang(angka/10) + " Puluh " + indexHurufTerbilang[angka.intValue() % 10];
        if(angka >= 100 && angka <= 199) return "Seratus " + getTerbilang(angka % 100);
        if(angka >= 200 && angka <= 999) return getTerbilang(angka/100) + " Ratus " + getTerbilang(angka % 100);
        if(angka >= 1000 && angka <= 1999) return "Seribu " + getTerbilang(angka%1000);
        if(angka >= 2000 && angka <= 999999) return getTerbilang(angka/1000) + " Ribu " + getTerbilang(angka % 1000);
        if(angka >= 1000000 && angka <= 999999999) return getTerbilang(angka/1000000) + " Juta " + getTerbilang(angka % 1000000);
        if(angka >= 1000000000 && angka <= 999999999999L) return getTerbilang(angka/1000000000) + " Milyar " + getTerbilang(angka % 1000000000);
        if(angka >= 1000000000000L && angka <= 999999999999999L) return getTerbilang(angka/1000000000000L) + " Triliun " + getTerbilang(angka % 1000000000000L);
        if(angka >= 1000000000000000L && angka <= 999999999999999999L) return getTerbilang(angka/1000000000000000L) + " Qudrilyun " + getTerbilang(angka % 1000000000000000L);
        return "";
    }
    
    public boolean isValidNumber(String number) {
        try {
            Integer.parseInt(number);
            Long.parseLong(number);
            Double.parseDouble(number);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean isRpFormat(String number){
        if(extractRpString(number).equals("")){
            return false;
        }else{
            return true;
        }
    }
    
    public static void main(String[] args){
        AlphaNumericUtility nu = new AlphaNumericUtility();
        String a = "gugun";
        String b = nu.formatRp(a);
        System.out.println(b);
        if(nu.isRpFormat(b)){
            System.out.println(nu.extractRpString(b));
        }else{
            System.out.println(b);
        }
        
    }
    

}

