package com.github.utils;

import java.util.Map;

public class QueryParameters {

    public static final int LINE_COUNT = 100000;

    public static final int MAX_SERVICE = 10;

    public static final int MAX_SERVICE_VARIATIONS = 3;

    public static final int MAX_QUESTIONS = 10;

    public static final int MAX_CATEGORIES = 20;

    public static final int MAX_SUBCATEGORIES = 5;

    public static final char WAITING_TIMELINE = 'C';

    public static final char QUERY = 'D';

    public static final String RESPONSE_FIRST = "P";

    public static final String RESPONSE_NEXT = "N";

    public static final Map<Integer, Integer> SERVICE_MAP = Map.of(
            0, MAX_SERVICE,
            1, MAX_SERVICE_VARIATIONS
    );

    public static final Map<Integer, Integer> QUESTION_MAP = Map.of(
            0, MAX_QUESTIONS,
            1, MAX_CATEGORIES,
            2, MAX_SUBCATEGORIES
    );
}
