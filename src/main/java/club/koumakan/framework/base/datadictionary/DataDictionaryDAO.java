package club.koumakan.framework.base.datadictionary;

import club.koumakan.framework.common.abstractapi.FrameworkDAOApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataDictionaryDAO extends JpaRepository<DataDictionary, Long>, FrameworkDAOApi {
}
