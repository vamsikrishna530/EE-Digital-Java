package com.raja.guestbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raja.guestbook.domain.GuestBookEntry;
import com.raja.guestbook.domain.GuestBookEntryRepository;

import java.util.List;

@Service
public class GuestBookService {

    @Autowired
    private GuestBookEntryRepository guestBookEntryRepository;

    public List <GuestBookEntry> findAllEntries () {
        return this.guestBookEntryRepository.findAll ();
    }

    public GuestBookEntry findGuestBookEntryById (Integer id) {
        return this.guestBookEntryRepository.findGuestBookEntryById (id);
    }

    public List<GuestBookEntry> findGuestBookEntriesByUser (String user) {
        return this.guestBookEntryRepository.findGuestBookEntriesByUser (user);
    }

    public void deleteGuestBookEntryById (Integer id) {
        this.guestBookEntryRepository.deleteGuestBookEntryById (id);
    }

    public void save (GuestBookEntry newEntry) {
        this.guestBookEntryRepository.save (newEntry);
    }

}
