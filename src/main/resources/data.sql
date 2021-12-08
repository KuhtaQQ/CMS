INSERT INTO page (id, url, title, priority) VALUES
    (1, 'homepage', 'Homepage', 1),
    (2, 'about', 'About',3),
    (3, 'contacts', 'Contacts',2);

SELECT * FROM page ORDER BY priority;


INSERT INTO page_text (id, content, page_id, identity, type) VALUES
    (1, 'Wellcome text', 1, 'wellcome-text', 'TEXT_BOX'),
    (2, 'Homepage text', 1, 'homepage-text', 'CK_EDITOR'),
    (3, 'About text', 2, 'about-text', 'CK_EDITOR'),
    (4, 'Contacts text', 3, 'contacts-text', 'CK_EDITOR');

INSERT INTO page_image (id, file_name, identity, path, page_id) VALUES
    (1,	'dummy1.png',	'about-image',	NULL,	2);