package club.koumakan.framework.base.datadictionary.controller;

import club.koumakan.framework.base.datadictionary.entity.DataDictionary;
import club.koumakan.framework.base.datadictionary.service.DataDictionaryService;
import club.koumakan.framework.common.abstractapi.impl.FrameworkControllerApiImpl;
import club.koumakan.framework.common.http.MsgResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/dataDictionary")
public class DataDictionaryController extends FrameworkControllerApiImpl<DataDictionary> {

    public DataDictionaryController(DataDictionaryService service) {
        super(service);
    }

    @Override
    public MsgResult<DataDictionary> save(@RequestBody DataDictionary dataDictionary) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String mapping = objectMapper.writeValueAsString(dataDictionary);
            dataDictionary.setMapping(mapping);

            return super.save(dataDictionary);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.error();
        }
    }
}
