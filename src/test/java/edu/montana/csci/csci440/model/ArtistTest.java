package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.DBTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistTest extends DBTest {

    @Test
    void testAllLoadsAllArtists() {
        List<Artist> all = Artist.all();
        assertEquals(347, all.size());
    }

    @Test
    void testPagingWorks() {
        assertEquals(100, Artist.all(1, 100).size());
        assertEquals(100, Artist.all(2, 100).size());
        assertEquals(100, Artist.all(3, 100).size());
        assertEquals(47, Artist.all(4, 100).size());
        assertEquals(0, Artist.all(5, 100).size());
    }

    @Test
    void testCreateWorks() {
        Artist artist = new Artist();

        artist.setName("Example");

        assertNull(artist.getArtistId());
        artist.create();
        assertNotNull(artist.getArtistId());

        assertEquals(artist.find(artist.getArtistId()), artist);
    }

    @Test
    void testValidationWorks() {
        Artist Artist = new Artist();

        assertFalse(Artist.verify());
        // expect a name
        assertEquals(1, Artist.getErrors().size());

        Artist.setName("Example");
        assertTrue(Artist.verify());
        assertEquals(0, Artist.getErrors().size());
    }

    @Test
    void testUpdateWorks() {
        Artist artist = Artist.find(1);
        String newName = "DC/AC";
        artist.setName(newName);
        artist.update();
        assertEquals(newName, Artist.find(1).getName());
    }

}
