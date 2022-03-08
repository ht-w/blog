package io.hongting.service;

import io.hongting.entity.Archive;
import io.hongting.entity.vo.ArchivesVO;

import java.util.List;
import java.util.Map;

/**
 * @author hongting
 * @create 2021 12 16 3:10 PM
 */


public interface ArchiveService {

   List<Archive> getArchives();
}
