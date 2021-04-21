package club.koumakan.framework.base.datadictionary;

import club.koumakan.framework.common.abstractapi.impl.FrameworkServiceApiImpl;
import org.springframework.stereotype.Service;

@Service
public class DataDictionaryService extends FrameworkServiceApiImpl<DataDictionary, DataDictionaryDAO> {

    public DataDictionaryService(DataDictionaryDAO dataDictionaryDAO) {
        super(dataDictionaryDAO);
    }

    @Override
    public void translate(DataDictionary entity) {

    }
}
