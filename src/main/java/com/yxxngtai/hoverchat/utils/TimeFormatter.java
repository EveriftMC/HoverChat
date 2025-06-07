package com.yxxngtai.hoverchat.utils;

public class TimeFormatter {
   public static String formatMinutes(int mins) {
      int hours = mins / 60;
      int days = hours / 24;
      StringBuilder formattedTime = new StringBuilder();
      if (days > 0) {
         formattedTime.append(days).append("d ");
         int remainingHours = hours - days * 24;
         formattedTime.append(remainingHours).append("h");
      } else {
         int remainingMinutes = mins % 60;
         if (hours > 0) {
            formattedTime.append(hours).append("h ");
         }

         formattedTime.append(remainingMinutes).append("m");
      }

      return formattedTime.toString();
   }
}
