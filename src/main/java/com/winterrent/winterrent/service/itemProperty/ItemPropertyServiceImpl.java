package com.winterrent.winterrent.service.itemProperty;

import com.winterrent.winterrent.dao.itemProperty.ItemPropertyDAO;
import com.winterrent.winterrent.entity.ItemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPropertyServiceImpl implements ItemPropertyService {
    private static final Logger logger = LoggerFactory.getLogger(ItemPropertyServiceImpl.class);

    private ItemPropertyDAO itemPropertyDao;

    @Autowired
    public ItemPropertyServiceImpl(ItemPropertyDAO theItemPropertyDao) {
        this.itemPropertyDao = theItemPropertyDao;
    }

    @Override
    public List<ItemProperty> findAll() {
        logger.info("Find all item properties");
        return itemPropertyDao.findAll();
    }
}
