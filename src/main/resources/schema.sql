CREATE TABLE IF NOT EXISTS Content (
    id INTEGER AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    desc TEXT,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255),
    PRIMARY KEY (id)
);

INSERRT INTO COntent (title, desc, status, content_type, date_created, url) VALUES
('Sample Article', 'This is a sample article description.', 'idea', 'article', CURRENT_TIMESTAMP, 'http://example.com/sample-article'),
('Another Article', 'Description for another article.', 'draft', 'article', CURRENT_TIMESTAMP, 'http://example.com/another-article');