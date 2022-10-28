package com.example.SpringBoot;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class VersionTest {
    static final String VersionNull = "The 'version' is null.";
    static final String VersionMatchPattern = "The 'version' format is 'major.minor.patch(-SNAPSHOT)'.";
    static final String pattern = "\\d+(\\.\\d+){0,2}(-SNAPSHOT)?";

    /*
     * asserThat(실제값).검증메서드(기댓값);
     * assertThatThrownBy(() -> 실행문);
     * assertThatThrownBy(() -> 실행문).isInstanceOf(익셉션.class);
     * assertThatExceptionOfType(익셉션.class).isThrownBy(() -> 실행문);
     * assertThat[특정입센션명].isThrownBy(() -> 실행문); //ex) IOExcption
     *
     * SoftAssertions.assertSoftly(soft -> {
	        soft.assertThat(1).isBetween(0, 2);
	        soft.assertThat(1).isBetween(2, 5);
        });
     */
    @Test
    public void basicTest() {
        String seatClass = "Economy";

        ArrayList<String> seatClassList = new ArrayList<>();
        seatClassList.add("Economy");
        seatClassList.add("Bussines");

        assertThat(seatClass).isEqualTo("Economy"); // 값이 같은지 검증
        assertThat(seatClass).isNotEqualTo("Bussines"); // 값이 같지 않은지 검증

        assertThat(seatClass).isIn(seatClassList); // 목록에 포함되어 있는지 검증
        assertThat("EconomyBussines").isNotIn(seatClassList); // 목록에 포함되어 있지 않은지 검증

        assertThat(seatClass).isNotNull(); // Null이 아닌지 검증
        seatClass = null;
        assertThat(seatClass).isNull(); // Null인지 검증
    }

    @Test
    public void compareTest(){
        int number = 100;

        assertThat(number).isLessThan(150); // 값보다 작은지 검증
        assertThat(number).isLessThanOrEqualTo(100); // 값보다 작거나 같은지 검증
        assertThat(number).isGreaterThan(86); // 값보다 큰지 검증
        assertThat(number).isGreaterThanOrEqualTo(100); // 값보다 크거나 같은지 검증
        assertThat(number).isBetween(90, 110); // 값1과 값2사이에 포함되는지 검증
    }

    @Test
    public void booleanTest(){
        assertThat(true).isTrue(); // 값이 true인지 검증
        assertThat(false).isFalse(); // 값이 false인지 검증
    }

    @Test
    public void stringTest(){
        /*
         * 특정 값을 포함하는지 검사
         */
        assertThat("Start testing.").contains("Start testing."); // 인자로 지정한 문자열들을 모두 포함하고 있는지 검증
        assertThat("Start testing.").containsOnlyOnce("a");	// 해당 문자열을 딱 한 번만 포함하는지 검증
        assertThat("123456789").containsOnlyDigits();	// 숫자만 포함하는지 검증
        assertThat("Start testing.").containsWhitespaces();	// 공백 문자를 포함하고 있는지 검증
        assertThat("              ").containsOnlyWhitespaces();	// 공백 문자만 포함하는지 검증
        assertThat("1.0.2").containsPattern(pattern);	// 지정한 정규 표현식에 일치하는 문자를 포함하는지 검증

        /*
         * 특정 값을 포함하지 않는지 검사
         */
        assertThat("Start testing.").doesNotContain("End");	// 인자로 지정한 문자열들을 포함하지 않는지 검증
        assertThat("Starttesting.").doesNotContainAnyWhitespaces(); // 공백 문자를 포함하고 있지 않은지 검증
        assertThat("Starttesting.").doesNotContainOnlyWhitespaces(); // 공백 문자만 포함하고 있지 않은지 검증
        assertThat("version").doesNotContainPattern(pattern); // 정규표현식에 일치하는 문자를 포함하지 않은지 검증

        /*
         * 특정문자열로 시작하거나 끝나는지 검사
         */
        assertThat("Start testing.").startsWith("Start"); // 지정한 문자열로 시작하는지를 검증
        assertThat("Start testing.").doesNotStartWith("End"); //	지정한 문자열로 시작하지 않는지 검증
        assertThat("Start testing.").endsWith("testing."); //지정한 문자열로 끝나는지를 검증
        assertThat("Start testing.").doesNotEndWith("Start"); //지정한 문자열로 끝나는지 않는지 검증
    }

    @Test
    public void numberTest(){
        assertThat(0).isZero(); // 0인지 검증
        assertThat(5).isNotZero(); // 0이 아닌지 검증
        assertThat(1).isOne(); // 1인지 검증
        assertThat(3).isPositive(); // 양수인지 검증
        assertThat(-1).isNotPositive(); // 양수가 아닌지 검증
        assertThat(-2).isNegative(); // 음수인지 검증
        assertThat(3).isNotNegative(); // 음수가 아닌지 검증
    }

    @Test
    public void DateTest(){
        //LocalDateTime에만 사용하는 검증
        LocalDateTime ldt = LocalDateTime.now();

        assertThat(ldt).isAfter(ldt.minusDays(1)); // 특정 시간값 이후인지 검증
        assertThat(ldt).isAfterOrEqualTo(ldt); // 비교값 이후인지 같은지 검증
        assertThat(ldt.minusDays(1)).isBefore(ldt); // 비교값보다 이전인지 검증
        assertThat(ldt).isBeforeOrEqualTo(ldt); // 비교값보다 이전인지 같은지 검증
    }

    @Test
    public void collectionTest(){
        ArrayList<Integer> A = new ArrayList<>();
        HashMap<Integer, Integer> B = new HashMap<>();
        for(int i= 1; i<=3; i++){
            A.add(i);
            B.put(i, i);
        }

        assertThat(A).hasSize(3); // 사이즈가 n과 같은지 검증
        assertThat(A).contains(1); //지정한 값을 포함하는지 검증
        assertThat(A).containsOnly(1, 2, 3); // 지정한 값만을 포함하는지 검증
        assertThat(A).containsAnyOf(2, 3); // 지정한 값 중 일부를 포함하는지 검증
        assertThat(A).containsOnlyOnce(1); // 지정한 값을 한 번만 포함하는지 검증

        assertThat(B).containsKey(1); // key를 포함하는지 검증
        assertThat(B).containsOnlyKeys(1, 2, 3); //지정한 key만 포함하는지 검증
        assertThat(B).doesNotContainKey(4); //지정한 키들을 포함하지 않는지 검증
        assertThat(B).containsValues(3); //values를 포함하는지 검증
        assertThat(B).contains(entry(1,1)); //Entry<K,V>를 포함하는지 검증
    }

    @Test
    public void softTest(){
        //여러검증 한번에 진행
        //Case1
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(1).isBetween(0, 2);
        soft.assertThat(3).isBetween(2, 5);
        soft.assertAll();

        //Case2
        SoftAssertions.assertSoftly(soft2 -> {
            soft2.assertThat(1).isBetween(0, 2);
            soft2.assertThat(3).isBetween(2, 5);
        });
    }

    @Test
    public void exceptionTest() {
        assertThatThrownBy(() -> {
            int number = Integer.valueOf("A"); // Exception이 발생하는 경우를 검증
        }).isInstanceOf(NumberFormatException.class);
    }

    @Test
    public void notExceptionTest() {
        assertThatCode(() -> {
            int number = Integer.valueOf("1"); // Exception이 발생하지 않는 경우를 검증
        }).doesNotThrowAnyException();
    }

    @Test
    public void nullExceptionTest() {
        assertThatThrownBy(() -> {
            Version version = new Version(null); // Null Exception 발생시 에러 메시지
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(VersionNull);
    }

    @Test
    public void nullExceptionTest2() {
        assertThatThrownBy(() -> {
            Version version = new Version("1.2.0");
            version.compareTo(null); // Null Exception 발생시 에러 메시지

        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VersionNull);
    }

    @Test
    public void patternExceptionTest() {
        assertThatThrownBy(() -> {
            Version version = new Version("1-1-0");
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(VersionMatchPattern);
    }

    @Test
    public void patternExceptionTest2() {
        assertThatThrownBy(() -> {
            Version version = new Version("1.1.0.0");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VersionMatchPattern);
    }

    @Test
    public void exampleTest4() {
        assertThat(new Version("1").isSnapshot()).isFalse();
        assertThat(new Version("1.2").isSnapshot()).isFalse();
        assertThat(new Version("1.2.0").isSnapshot()).isFalse();
        assertThat(new Version("1.2.0-SNAPSHOT").isSnapshot()).isTrue();
    }

    @Test
    public void exampleTest6() {
        assertThat(new Version("1.2.0").compareTo(new Version("1.2.0"))).isZero();
        assertThat(new Version("1.2.0").compareTo(new Version("1.2"))).isZero();

        assertThat(new Version("1.2.0").compareTo(new Version("1.2.1"))).isNegative();
        assertThat(new Version("1.2").compareTo(new Version("2.3.0"))).isNegative();

        assertThat(new Version("1.2.0").compareTo(new Version("1.1.0"))).isPositive();
        assertThat(new Version("1.2").compareTo(new Version("1.2.0-SNAPSHOT"))).isPositive();
    }
}