INSERT INTO keyword_scan_summary_status (id, name) VALUES (5, 'Parse Failed');

ALTER TABLE keyword_scan_summary
ADD COLUMN num_parse_fails INT NULL AFTER cache_page;

INSERT INTO searchengine_parameter (id, value) VALUES ('MAX_PARSE_FAILS', '5');

