package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.dao.GlobalDictionaryDao;
import com.sevenXnetworks.treasure.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private GlobalDictionaryDao dictionaryDao;

}
