package com.example.common.util;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.example.blog.entity.Blog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ElasticUtil {
    /**
     * 操作elastsearch数据库，默认数据库blog_db
     */
    private static final String DB_NAME = "blog_db";

    @Resource
    RedisUtils redisUtils;
    @Resource
    ElasticsearchClient elasticsearchClient;

    /**
     * 创建数据库
     */
    public void create() throws IOException {
        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(c -> c.index(DB_NAME));
        Boolean acknowledged = createIndexResponse.acknowledged();
        System.out.println("索引操作 = " + acknowledged);
    }

    /**
     *搜寻数据库中所有数据
     */
    public void search() throws IOException {
        GetIndexResponse getIndexResponse = elasticsearchClient.indices().get(e -> e.index(DB_NAME));
        System.out.println("getIndexResponse.result() = " + getIndexResponse.result());
        System.out.println("getIndexResponse.result().keySet() = " + getIndexResponse.result().keySet());
    }

    /**
     *删除数据库
     */
    public void delete() throws IOException {
        DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices().delete(e -> e.index(DB_NAME));
        System.out.println("删除操作 = " + deleteIndexResponse.acknowledged());
    }

    /**
     * 插入一条博客数据
     * @param blog 博客实体类
     */
    public void insertDoc(Blog blog) throws IOException {
        blog.setContext(null);
        //elasticsearch中不存具体内容
        CreateResponse createResponse = elasticsearchClient.create(e -> e.index(DB_NAME).id(String.valueOf(blog.getBid())).document(blog));
        //向数据库中插入一条blog，以bid为主键
        System.out.println("createResponse.result() = " + createResponse.result());
    }

//    public void getDoc(int userid) throws IOException {
//        GetResponse<User> getResponse = elasticsearchClient.get(e -> e.index(DB_NAME).id(String.valueOf(userid)), User.class);
//        System.out.println("getResponse.source().toString() = " + getResponse.source().toString());
//    }

    /**
     * 更新博客
     * @param blog 博客实体类
     */
    public void updateDoc(Blog blog) throws IOException {
        blog.setId(redisUtils.getUser().getId());
        blog.setAddtime(TimeUtil.getTime());
        blog.setHot(0);
        blog.setIsalive(0);
        UpdateResponse<Blog> updateResponse = elasticsearchClient.update(e -> e
                .index(DB_NAME)
                .id(String.valueOf(blog.getBid()))
                .doc(blog), Blog.class);
        System.out.println("updateResponse.result() = " + updateResponse.result());

    }

    /**
     * 根据bid删除一条博客
     * @param bid 博客编号
     */
    public void deleteDoc(int bid) throws IOException {
        DeleteResponse deleteResponse = elasticsearchClient.delete(e -> e.index(DB_NAME).id(String.valueOf(bid)));
        System.out.println("deleteResponse.result() = " + deleteResponse.result());
    }

    /**
     * 批量插入博客
     * @param blogList 博客列表
     * @throws IOException
     */
    public void batchAddDoc(List<Blog> blogList) throws IOException {
        List<BulkOperation> list = new ArrayList<>();
        for (Blog blog:blogList) {
            list.add(new BulkOperation.Builder().create(
                    d -> d.document(blog).id(String.valueOf(blog.getBid())).index(DB_NAME)
                            )
                    .build()
            );
        }
        // 调用bulk方法执行批量插入操作
        BulkResponse bulkResponse = elasticsearchClient.bulk(e -> e.index(DB_NAME).operations(list));
        System.out.println("bulkResponse.items() = " + bulkResponse.items());
    }


    public void deleteBantchDoc(List<Integer> lists) throws IOException {
        List<BulkOperation> list = new ArrayList<>();
        for (int i : lists) {
            list.add(new BulkOperation.Builder().delete(
                    d -> d.id(String.valueOf(i)).index(DB_NAME)).build());
        }
        // 调用bulk方法执行批量插入操作
        elasticsearchClient.bulk(e -> e.index(DB_NAME).operations(list));

    }
    /**
     * 全量查询
     * @return 数据库中所有数据
     */
    public List<Blog> queryAllDoc() throws IOException {
        SearchResponse<Blog> searchResponse = elasticsearchClient.search(e -> e.index(DB_NAME).query(q -> q.matchAll(m -> m)), Blog.class);
        HitsMetadata<Blog> hits = searchResponse.hits();
        List<Blog> blogList = new ArrayList<>();
        for (Hit<Blog> hit : hits.hits()) {
            blogList.add(hit.source());
        }
        System.out.println("ListSize " + searchResponse.hits().total().value());
        return blogList;
    }

    /**
     * 标题名模糊查询
     * @param name 标题名
     * @param num 页数
     * @return 模糊查询数据
     */
    public Map<String,Object> fuzzQueryDoc(String name,int num) throws IOException {
        // 模糊查询，fuzziness表示差几个可以查询出来
        List<Blog> blogList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        SearchResponse<Blog> searchResponse = elasticsearchClient.search(s -> s
                        .index("user_db")
                        .query(q -> q
                            .fuzzy(f -> f
                                .field("title")
                                .value(name)
                                .fuzziness("5")))
                        .from(num)
                        .size(10)
                , Blog.class);
        HitsMetadata<Blog> hits = searchResponse.hits();
        for (Hit<Blog> hit : hits.hits()) {
            blogList.add(hit.source());
        }
        map.put("BlogList",blogList);
        map.put("BlogListSize",blogList.size());
        return map;
    }

    /**
     * 排序模糊查询，tag可选hot addtime
     * @param name 标题
     * @param num 页数
     * @param tag 排序标签
     */
    public Map<String,Object> fuzzQueryDocOrder(String name,int num,String tag) throws IOException {
        List<Blog> blogList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        SearchResponse<Blog> searchResponse = elasticsearchClient.search(s -> s
                        .index("user_db")
                        .query(q -> q
                                .fuzzy(f -> f
                                        .field("title")
                                        .value(name)
                                        .fuzziness("5")))
                        .sort(o -> o.field( f -> f.field(tag).order(SortOrder.Desc)))
                        .from(num)
                        .size(10)
                , Blog.class);
        HitsMetadata<Blog> hits = searchResponse.hits();
        for (Hit<Blog> hit : hits.hits()) {
            blogList.add(hit.source());
        }
        map.put("BlogList",blogList);
        map.put("BlogListSize",blogList.size());
        return map;
    }
//    public void highlightQuery() throws IOException {
//        SearchResponse<User> searchResponse = elasticsearchClient.search(s -> s.index("user_db").query(q -> q
//                                .term(t -> t.field("name").value("女")))
//                        .highlight(h -> h.fields("name", f -> f.preTags("<font color='red'>").postTags("</font>")))
//                , User.class);
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));
//    }
}
