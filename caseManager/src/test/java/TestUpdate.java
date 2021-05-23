import com.testcy.bean.UpdateUserInfoCase;
import com.testcy.bean.User;
import com.testcy.mapper.CaseMapper;
import com.testcy.mapper.UserMapper;
import com.testcy.utils.DBUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class TestUpdate {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = DBUtil.getSqlSession();
        CaseMapper caseMapper = sqlSession.getMapper(CaseMapper.class);
        UpdateUserInfoCase updateUserInfoCase = caseMapper.updateUserInfoCase(2);
        System.out.println(updateUserInfoCase);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User updateUserInfo = mapper.getUpdateUserInfo(updateUserInfoCase);
        System.out.println(updateUserInfo);
    }

}
