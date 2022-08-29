package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsjyz.hnnu.mapper.ArchivesMapper;
import com.jsjyz.hnnu.mapper.KeyWordsMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.pojo.KeyWords;
import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesListVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Autowired
    private KeyWordsMapper keyWordsMapper;
    @Autowired
    private ArchivesMapper archivesMapper;
    @Override
    public Map<String, List<ArchivesListVo>> getArchives(PaginationVo pagination) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getIsArchived,true);
        formLambdaQueryWrapper.orderByDesc(Form::getCreateTime);
        formLambdaQueryWrapper.eq(Form::getIsArchived,1);
        Page<Form> formPage = new Page<>(pagination.getPageNum(), pagination.getPageSize());
        IPage<Form> page = archivesMapper.selectPage(formPage, formLambdaQueryWrapper);
        List<Form> records = page.getRecords();
        //data convert
        ArrayList<ArchivesListVo> archivesListVos = new ArrayList<>();
        for (Form form :
                    records) {
            ArchivesListVo archivesListVo = new ArchivesListVo();
            archivesListVo.setIsArchived(form.getIsArchived().toString());
                BeanUtils.copyProperties(form, archivesListVo);
            archivesListVos.add(archivesListVo);
            }
        ;
        //group
        Map<String, List<ArchivesListVo>> postsPerType = archivesListVos.stream()
                .collect(Collectors.groupingBy(item -> {
                    LocalDateTime localDateTime = item.getCreateTime().toLocalDateTime();
                    String dateString = String.valueOf(localDateTime.getYear()) + "-" + String.valueOf(localDateTime.getMonthValue());

                    return dateString;
                }));
        return  postsPerType;
    }

    @Override
    public List<ArchivesListVo> searchArticles(String searchContent,PaginationVo paginationVo) {
        //select articleId
        LambdaQueryWrapper<KeyWords> keyWordsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        keyWordsLambdaQueryWrapper.like(KeyWords::getKeyWords, searchContent);
        keyWordsLambdaQueryWrapper.select(KeyWords::getArticleId);
        List<KeyWords> keyWords = keyWordsMapper.selectList(keyWordsLambdaQueryWrapper);
        //convert List<KeyWords> to List<Long> because formLambdaQueryWrapper.in(Form::getId,keyWordsInteger);
        List<Long> keyWordsInteger = new ArrayList<>();
        keyWords.forEach(keyword ->{
            keyWordsInteger.add(keyword.getArticleId());
        });
        //select searched article
        List<Form> forms = new ArrayList<>();
        if (!keyWordsInteger.isEmpty()) {
            LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
            formLambdaQueryWrapper.in(Form::getId,keyWordsInteger);
            Page<Form> keyWordsPage = new Page<>(paginationVo.getPageNum(), paginationVo.getPageSize());
            IPage<Form> page = archivesMapper.selectPage(keyWordsPage, formLambdaQueryWrapper);
           forms = page.getRecords();
        }
        //data convert
        ArrayList<ArchivesListVo> archivesListVos = new ArrayList<>();
        for (Form form :
                forms) {
            ArchivesListVo archivesListVo = new ArchivesListVo();
            archivesListVo.setIsArchived(form.getIsArchived().toString());
            BeanUtils.copyProperties(form, archivesListVo);
            archivesListVos.add(archivesListVo);
        }
        ;
        return archivesListVos;
    }
}
