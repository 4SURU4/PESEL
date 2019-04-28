package com.suru.validator;

import com.suru.validator.exception.InvalidPeselException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PESELTest {

    private final String VALID_MALE_PESEL = "93082010575";
    private final String INVALID_FEMALE_PESEL = "94121008062";
    private final String VALID_FEMALE_PESEL = "94121008063";

    private PESEL malePESEL;
    private PESEL femalePESEL;

    @Before
    public void init() throws InvalidPeselException {
        malePESEL = new PESEL(VALID_MALE_PESEL);
        femalePESEL = new PESEL(VALID_FEMALE_PESEL);
    }

    @Test
    public void constructor_shouldThrowInvalidPeselException() {
        //given

        //when
        Throwable throwable = catchThrowable(() ->new PESEL(INVALID_FEMALE_PESEL));

        //then
        assertThat(throwable).isInstanceOf(InvalidPeselException.class);
    }

    @Test
    public void constructor_shouldCreatePESELObjectWithoutErrors() {
        //given

        //when
        Throwable throwable = catchThrowable(() ->new PESEL(VALID_FEMALE_PESEL));

        //then
        assertThat(throwable).isNull();
    }

    @Test
    public void getSex_shouldReturnMaleSex() {
        //given
        //when
        String sex = malePESEL.getSex();

        //then
        assertThat(sex).isEqualTo("M");
    }

    @Test
    public void getSex_shouldReturnFemaleSex() {
        //given
        //when
        String sex = femalePESEL.getSex();

        //then
        assertThat(sex).isEqualTo("F");
    }

    @Test
    public void getBirthDate_shouldReturnCorrectBirthDate() {
        //given
        //when
        String birthDate = malePESEL.getBirthDate();

        //then
        assertThat(birthDate).isEqualTo("20.08.1993");
    }

    @Test
    public void getDay_shouldReturnCorrectBirthDay() {
        //given
        //when
        int birthDay = malePESEL.getDay();

        //then
        assertThat(birthDay).isEqualTo(20);
    }

    @Test
    public void getMonth_shouldReturnCorrectBirthMonth() {
        //given
        //when
        int birthMonth = femalePESEL.getMonth();

        //then
        assertThat(birthMonth).isEqualTo(12);
    }

    @Test
    public void getYear_shouldReturnCorrectBirthYear() {
        //given
        //when
        int birthYear = femalePESEL.getYear();

        //then
        assertThat(birthYear).isEqualTo(1994);
    }
}