package com.laptrinhjavaweb.orm.query.criteria.criterion;

public enum MatchMode {
    EXACT {
        public String toMatchString(String pattern) {
            return pattern;
        }
    },
    START {
        public String toMatchString(String pattern) {
            return pattern + '%';
        }
    },
    END {
        public String toMatchString(String pattern) {
            return '%' + pattern;
        }
    },
    ANYWHERE {
        public String toMatchString(String pattern) {
            return '%' + pattern + '%';
        }
    };

    private MatchMode() {
    }

    public abstract String toMatchString(String value);
}
