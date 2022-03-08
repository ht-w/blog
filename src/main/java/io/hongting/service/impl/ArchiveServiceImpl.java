package io.hongting.service.impl;


import io.hongting.entity.Archive;
import io.hongting.entity.vo.ArchivesVO;
import io.hongting.mapper.PostMapper;
import io.hongting.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author hongting
 * @create 2021 12 16 3:10 PM
 */

@Service
public class ArchiveServiceImpl implements ArchiveService {

   @Autowired
   private PostMapper postMapper;

    @Override
    public List<Archive> getArchives() {
        List<ArchivesVO> archivesVO = postMapper.getArchives();
        List<Archive> archives = new ArrayList<>();
        String current = "";
        for (ArchivesVO vo : archivesVO) {
            String year = String.valueOf(vo.getUpdateTime().getYear());
            if (year.equals(current)) {
                Archive archive = archives.get(archives.size() - 1);
                archive.getArchives().add(vo);
                archive.setCount(archive.getArchives().size());
            } else {
                current = year;
                Archive archive = new Archive();
                archive.setYear(year);
                archive.setCount(1);
                List<ArchivesVO> arc = new ArrayList<>();
                arc.add(vo);
                archive.setArchives(arc);
                archives.add(archive);
            }

        }
        return archives;


    }
}
