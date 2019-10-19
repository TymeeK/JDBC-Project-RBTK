/* This table holds information related to different writing groups */
CREATE TABLE WritingGroups(
  group_name    VARCHAR(50) NOT NULL, 
  head_writer   VARCHAR(50) NOT NULL,
  year_formed   YEAR,
  subject       VARCHAR(50),
  CONSTRAINT writing_groups_pk PRIMARY KEY(group_name),
  CONSTRAINT writing_groups_uk UNIQUE (group_name, head_writer)
);

/* This table holds information related to different publishers */
CREATE TABLE Publishers(
  publisher_name    VARCHAR(50) NOT NULL,
  publisher_address VARCHAR(50),
  publisher_phone   VARCHAR(20),
  publisher_email   VARCHAR(50),
  CONSTRAINT publishers_fk PRIMARY KEY(publisher_name)
);

/* This table holds information related to different books */
CREATE TABLE Books(
  group_name        VARCHAR(50) NOT NULL,
  book_title        VARCHAR(50) NOT NULL,
  publisher_name    VARCHAR(50) NOT NULL,
  year_published    SMALLINT, 
  number_pages      SMALLINT,
  CONSTRAINT books_pk PRIMARY KEY(group_name, book_title), 
  CONSTRAINT books_uk UNIQUE(book_title, publisher_name),
  CONSTRAINT books_writing_groups_fk01 FOREIGN KEY(group_name) 
  REFERENCES WritingGroups(group_name),
  CONSTRAINT books_publishers_fk02 FOREIGN KEY(publisher_name) 
  REFERENCES Publishers(publisher_name)
);

INSERT INTO WritingGroups (group_name, head_writer, year_formed, subject)
    VALUES ('Writers Unite',        'Shel Silverstein', 1974,  'Poetry'),
           ('Write On',             'John Green',       2005,  'Young Adult'),
           ('Authors Anonymous',    'J.K. Rowling',     1997,  'Fantasy'),
           ('Romance Reporters',    'Nicholas Sparks',  1996,  'Romance'),
           ('History Hounds',       'James Randi',      1987,  'History'),
           ('Science Lovers',       'Stephen Hawking',  2018,  'Science');

INSERT INTO Publishers (publisher_name, publisher_address, publisher_phone, publisher_email)
    VALUES ('Penguin Random House',     '1745 Broadway St. New York, NY 10019',            '212-782-9000',  'penguin@gmail.com'),
           ('Hachette Livre',           '1290 6th Ave. New York, NY 10104',                '212-364-1100',  'hachette@gmail.com'),
           ('HarperCollins',            '195 Broadway St. New York, NY 10007',             '212-207-7000',  'harperc@gmail.com'),
           ('Macmillan Publishers',     '39 Plumb Branch Ave. Huntington Beach, CA 92646', '582-702-5870',  'macmil@gmail.com'),
           ('Simon & Schuster',         '843 Glenlake Rd. San Diego, CA 92105',            '441-818-1042',  'simonschuster@gmail.com'),    
           ('McGraw-Hill Education',    '8468 Columbia Dr. Fontana, CA 92335',             '623-310-2380',  'mcgrawhill@gmail.com');
          
    
INSERT INTO Books (group_name, book_title, publisher_name, year_published, number_pages)
    VALUES ('Writers Unite',      'Poetry 101',               'Penguin Random House',   2000,  120),
           ('Writers Unite',      'Love and Poetry',          'Hachette Livre',         2018,  278),
           ('Write On',           'The Fault in our Stars',   'Penguin Random House',   2012,  425),
           ('Write On',           'Looking for Alaska',       'HarperCollins',          2005,  346),
           ('Authors Anonymous',  'Sourcerers Stone',         'Hachette Livre',         1999,  636),
           ('Authors Anonymous',  'Chamber of Secrets',       'HarperCollins',          2001,  754),
           ('Romance Reporters',  'The Last Song',            'Macmillan Publishers',   2008,  320),
           ('Romance Reporters',  'Safe Haven',               'Simon & Schuster',       2010,  410),
           ('History Hounds',     'Flat Earth',               'McGraw-Hill Education',  2019,  80),
           ('History Hounds',     'The Revolution',           'Macmillan Publishers',   1986,  240),
           ('Science Lovers',     'Mitosis',                  'McGraw-Hill Education',  1980,  280),
           ('Science Lovers',     'Hypothesis',               'Simon & Schuster',       2002,  565);

