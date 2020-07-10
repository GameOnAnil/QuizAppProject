package com.example.quizproject;


import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "category1";
        public static final String TABLE_NAME2 = "category2";
        public static final String TABLE_NAME3 = "category3";
        public static final String TABLE_NAME4 = "category4";
        public static final String TABLE_NAME5 = "category5";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
