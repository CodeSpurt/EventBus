package com.codespurt.eventbus.model;

/**
 * Created by CodeSpurt on 09-08-2017.
 */

public class SamplePojo {

    public static class ActivityToFragmentData {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class FragmentToActivityData {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
