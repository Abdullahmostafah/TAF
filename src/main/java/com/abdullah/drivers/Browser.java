package com.abdullah.drivers;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    },
    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FirefoxFactory();
        }
    },
    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new EdgeFactory();
        }
    },
    SAFARI {
        @Override
        public AbstractDriver getDriverFactory() {
            return new SafariFactory();
        }
    };

    public abstract AbstractDriver getDriverFactory();
}



