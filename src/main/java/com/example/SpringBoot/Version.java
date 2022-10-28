package com.example.SpringBoot;

import java.util.regex.Pattern;

public class Version implements Comparable<Version> {
    static final String VersionNull = "The 'version' is null.";
    static final String VersionMatchPattern =  "The 'version' format is 'major.minor.patch(-SNAPSHOT)'.";
    public final int[] numbers;
    public String version;

    Version(String version){
        String pattern = "\\d+(\\.\\d+){0,2}(-SNAPSHOT)?";

        if (null == version) {
            IllegalArgumentException(VersionNull);
        }

        boolean regex = Pattern.matches(pattern, version);

        if(false == regex){
            IllegalArgumentException(VersionMatchPattern);
        }

        String split[] = version.split("\\-")[0].split("\\.");
        numbers = new int[split.length];

        for(int i = 0; i<split.length; i++){
            numbers[i] = Integer.valueOf(split[i]);
        }

        this.version = version;
    }

    public boolean isSnapshot(){
        return this.version.endsWith("-SNAPSHOT");
    }

    @Override
    public int compareTo(Version other) {
        //0은 this == other
        //1은 this > other
        //-1은 this < other
        int compare = 0;

        //6-1
        if(null == other){
            IllegalArgumentException(VersionNull);
        }

        int leftLength = this.numbers.length;
        int rightLength = other.numbers.length;
        int maxLength = Math.max(leftLength, rightLength);

        for (int i = 0; i < maxLength; i++) {
            int leftVersion = i < this.numbers.length ? this.numbers[i] : 0;
            int rightVersion = i < other.numbers.length ? other.numbers[i] : 0;

            if (leftVersion != rightVersion) {
                compare = leftVersion > rightVersion ? 1 : -1;
                break;
            }
        }

        if(0 == compare){
            if(this.isSnapshot() && false == other.isSnapshot()){
                compare = -1;
            }else if(false == this.isSnapshot() && other.isSnapshot()){
                compare = 1;
            }
        }

        return compare;
    }

    public void IllegalArgumentException(String message){
        //throw new IllegalArgumentException(message);
        throw new IllegalArgumentException();
    }
}