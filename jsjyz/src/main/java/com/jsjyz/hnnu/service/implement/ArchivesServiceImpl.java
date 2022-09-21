package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsjyz.hnnu.mapper.FormMapper;
import com.jsjyz.hnnu.mapper.KeyWordsMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.pojo.KeyWords;
import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesListVo;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import org.apache.commons.lang3.StringUtils;
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
    private FormMapper formMapper;
    @Override
    public Map<String, Object> getArchives(PaginationVo pagination, String question, String tag) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();

        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        if (!StringUtils.isBlank(question))    {
            formLambdaQueryWrapper.like(Form::getTitle,question);
        }
        if (!StringUtils.isBlank(tag))    {
            formLambdaQueryWrapper.eq(Form::getTag,tag);
        }
        formLambdaQueryWrapper.eq(Form::getIsArchived,1);
        Long pageCount = formMapper.selectCount(formLambdaQueryWrapper);
        formLambdaQueryWrapper.orderByDesc(Form::getCreateTime);
        Page<Form> formPage = new Page<>(pagination.getPageNum(), pagination.getPageSize());
        formLambdaQueryWrapper.select(Form::getId,Form::getTitle,Form::getStatus,Form::getQuestion,Form::getCreateTime,Form::getTag);
        IPage<Form> page = formMapper.selectPage(formPage, formLambdaQueryWrapper);

        List<Form> records = page.getRecords();
        //data convert
        ArrayList<ArchivesListVo> archivesListVos = new ArrayList<>();
        List<ArchivesVo> archivesVos = new ArrayList<>();
        for (Form form :
                records) {
            ArchivesListVo archivesListVo = new ArchivesListVo();
            BeanUtils.copyProperties(form, archivesListVo);
            archivesListVos.add(archivesListVo);
        }
        if (archivesListVos.isEmpty()){
            return new HashMap<>();
        }
    // group
    Map<String, List<ArchivesListVo>> postsPerType =
        archivesListVos.stream()
            .collect(
                Collectors.groupingBy(
                    item -> {
                      LocalDateTime localDateTime = item
                              .getCreateTime()
                              .toLocalDateTime();
                      String dateString =
                          localDateTime.getYear()
                              + "-"
                              + localDateTime.getMonthValue();
                      return dateString;
                    }));

        postsPerType.forEach( (key,value) -> {
            ArchivesVo archivesVo = new ArchivesVo();
            archivesVo.setDateString(key);
            archivesVo.setArchivesListVo(value);
            archivesVos.add(archivesVo);
        });
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("archives", archivesVos);
        stringObjectMap.put("pageCount",pageCount);
        return  stringObjectMap;
    }

    @Override
    public List<ArchivesListVo> searchArticles(String searchContent,PaginationVo paginationVo) {
        //select articleId
        LambdaQueryWrapper<KeyWords> keyWordsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        keyWordsLambdaQueryWrapper.like(KeyWords::getKeyWords, searchContent);
        keyWordsLambdaQueryWrapper.eq(KeyWords::getDeleted,0);
        keyWordsLambdaQueryWrapper.select(KeyWords::getArticleId);
        List<KeyWords> keyWords = keyWordsMapper.selectList(keyWordsLambdaQueryWrapper);
        //convert List<KeyWords> to List<Long> because formLambdaQueryWrapper.in(Form::getId,keyWordsInteger);
        List<Long> keyWordsInteger = new ArrayList<>();
        keyWords.forEach(keyword -> keyWordsInteger.add(keyword.getArticleId()));
        //select searched article
        List<Form> forms = new ArrayList<>();
        if (!keyWordsInteger.isEmpty()) {
            LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
            formLambdaQueryWrapper.eq(Form::getIsArchived,0);
            formLambdaQueryWrapper.in(Form::getId,keyWordsInteger);
            Page<Form> keyWordsPage = new Page<>(paginationVo.getPageNum(), paginationVo.getPageSize());
            IPage<Form> page = formMapper.selectPage(keyWordsPage, formLambdaQueryWrapper);
           forms = page.getRecords();
        }
        //data convert
        ArrayList<ArchivesListVo> archivesListVos = new ArrayList<>();
        for (Form form :
                forms) {
            ArchivesListVo archivesListVo = new ArchivesListVo();
            BeanUtils.copyProperties(form, archivesListVo);
            archivesListVos.add(archivesListVo);
        }
        return archivesListVos;
    }

    @Override
    public Long getTotalPages() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        formLambdaQueryWrapper.eq(Form::getIsArchived,1);
        Long aLong = formMapper.selectCount(formLambdaQueryWrapper);
        return aLong;
    }

    @Override
    public List<String> getTags() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        formLambdaQueryWrapper.eq(Form::getIsArchived,1);
        formLambdaQueryWrapper.select(Form::getTag);
        List<Form> forms = formMapper.selectList(formLambdaQueryWrapper);
        Set<String> set = new HashSet<>();
        ArrayList<String> strings = new ArrayList<>();
        forms.forEach(form -> {
            set.add(form.getTag());
        });
        strings.addAll(set);
        return strings;
    }



    @Override
    public List<Form> getNewArticles() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        formLambdaQueryWrapper.select(Form::getTitle,Form::getId,Form::getCreateTime);
        formLambdaQueryWrapper.eq(Form::getIsArchived,1);
        formLambdaQueryWrapper.orderByDesc(Form::getCreateTime);
        formLambdaQueryWrapper.last("limit 5");
        List<Form> forms = formMapper.selectList(formLambdaQueryWrapper);
        return forms;

    }
}
