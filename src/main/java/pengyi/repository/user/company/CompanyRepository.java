package pengyi.repository.user.company;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.company.ICompanyRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("companyRepository")
public class CompanyRepository extends AbstractHibernateGenericRepository<Company, String>
        implements ICompanyRepository<Company, String> {
}
