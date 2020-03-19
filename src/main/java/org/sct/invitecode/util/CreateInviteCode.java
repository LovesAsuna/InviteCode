package org.sct.invitecode.util;

import java.util.Random;

public class CreateInviteCode {
    public static String createInviteCode() {
        int maxNum = 36;
        int i;
        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer code = new StringBuffer("");
        Random r = new Random();
        while (count < 10) {
            i = Math.abs(r.nextInt(maxNum)); // 0到36的随机数
            if (i >= 0 && i < str.length) {//如果i>=0且i<36
                code.append(str[i]);
                count++;
            }
        }
        return code.toString();
    }
}
