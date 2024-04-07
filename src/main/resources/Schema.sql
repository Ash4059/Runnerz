CREATE TABLE IF NOT EXISTS `Run` (
    id INTEGER PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    startDateTime timestamp NOT NULL,
    completedDateTime timestamp NOT NULL,
    miles INTEGER NOT NULL,
    location VARCHAR(10) NOT NULL
);
