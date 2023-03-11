package com.example.song.service;

import org.springframework.stereotype.Service;
import com.example.song.repository.*;
//import com.example.song.repository.SongJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.model.*;
import org.springframework.http.HttpStatus;

@Service
public class SongJpaService implements  SongRepository{
@Autowired
private SongJpaRepository songRepo;
@Override
public ArrayList<Song> getSongs(){
    List<Song> songList = songRepo.findAll();
    ArrayList<Song> songs = new ArrayList<>(songList);
    return songs;


}
@Override
public Song getSongById(int songId){
    try{
   Song song = songRepo.findById(songId).get();
    return song;
    }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
 
}
@Override
public Song addSong(Song song){
    songRepo.save(song);
    return song;
}
@Override
public Song updateSong(int songId, Song song){
    
    try{

        Song newSong = songRepo.findById(songId).get();
    if(song.getSongName()!=null){
        newSong.setSongName(song.getSongName());
    }
    if(song.getLyricist()!=null){
        newSong.setLyricist(song.getLyricist());
    }
    if(song.getSinger()!=null){
        newSong.setSinger(song.getSinger());
    }
    if(song.getMusicDirector()!= null){
        newSong.setMusicDirector(song.getMusicDirector());
    }
    return newSong;
    }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
    }
}
@Override
public void deleteSong(int songId){
    try{
songRepo.deleteById(songId);
    }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    
}




}

