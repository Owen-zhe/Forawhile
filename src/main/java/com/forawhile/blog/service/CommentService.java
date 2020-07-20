package com.forawhile.blog.service;

import com.forawhile.blog.po.Comment;

import java.util.List;
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
