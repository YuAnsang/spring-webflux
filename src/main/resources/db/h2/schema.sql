CREATE TABLE IF NOT EXISTS BOOK
(
    BOOK_ID          bigint       NOT NULL AUTO_INCREMENT,
    TITLE_KOREAN     varchar(100) NOT NULL,
    TITLE_ENGLISH    varchar(100) NOT NULL,
    DESCRIPTION      varchar(100) NOT NULL,
    AUTHOR           varchar(100) NOT NULL,
    ISBN             varchar(100) NOT NULL UNIQUE,
    PUBLISH_DATE     varchar(100) NOT NULL,
    CREATED_AT       datetime     NOT NULL,
    LAST_MODIFIED_AT datetime     NOT NULL,
    PRIMARY KEY (BOOK_ID)
);