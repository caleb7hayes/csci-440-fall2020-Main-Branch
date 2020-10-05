
INSERT INTO tracks
    (Name, AlbumId, MediaTypeId, GenreId,
     Composer, Milliseconds, Bytes, UnitPrice)
    VALUES
    ("Demo", 1, 1, 1, NULL, 2000, 300, .99)

UPDATE tracks
SET Bytes=(Bytes - 10)
WHERE TrackId = 1;

UPDATE tracks
SET Bytes=(Bytes + 10)
WHERE TrackId = 2;



BEGIN TRANSACTION;

UPDATE tracks
SET Bytes=(Bytes - 10)
WHERE TrackId = 1;

UPDATE tracks
SET Bytes=(Bytes + 10)
WHERE TrackId = 2;

COMMIT;


