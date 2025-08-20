package kakao.rebit.diary.service;

import java.time.LocalDate;
import java.util.List;
import kakao.rebit.book.entity.Book;
import kakao.rebit.book.exception.book.BookNotFoundException;
import kakao.rebit.book.repository.BookRepository;
import kakao.rebit.diary.dto.DiaryBookResponse;
import kakao.rebit.diary.dto.DiaryRequest;
import kakao.rebit.diary.dto.DiaryResponse;
import kakao.rebit.diary.entity.Diary;
import kakao.rebit.diary.exception.DiaryNotFoundException;
import kakao.rebit.diary.repository.DiaryRepository;
import kakao.rebit.member.entity.Member;
import kakao.rebit.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberService memberService;
    private final BookRepository bookRepository;

    public DiaryService(DiaryRepository diaryRepository, MemberService memberService,
            BookRepository bookRepository) {
        this.diaryRepository = diaryRepository;
        this.memberService = memberService;
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<DiaryResponse> getDiaries(Long memberId, LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        return diaryRepository.findByMemberIdAndYearAndMonth(memberId, year, month)
                .stream().map(this::toDiaryResponse).toList();
    }

    @Transactional(readOnly = true)
    public DiaryResponse getDiaryById(Long memberId, Long id) {
        Diary diary = diaryRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        return toDiaryResponse(diary);
    }

    @Transactional
    public Long createDiary(Long memberId, DiaryRequest diaryRequest) {
        Member member = memberService.findMemberByIdOrThrow(memberId);

        Book book = bookRepository.findByIsbn(diaryRequest.isbn())
                .orElseThrow(() -> BookNotFoundException.EXCEPTION);

        Diary diary = toDiary(member, book, diaryRequest);
        Diary savedDiary = diaryRepository.save(diary);

        return savedDiary.getId();
    }

    @Transactional
    public void updateDiary(Long memberId, Long id, DiaryRequest diaryRequest) {
        Diary diary = diaryRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);

        Book book = bookRepository.findByIsbn(diaryRequest.isbn())
                .orElseThrow(() -> BookNotFoundException.EXCEPTION);

        diary.updateDiary(diaryRequest.content(), book);
    }

    @Transactional
    public void deleteDiary(Long memberId, Long id) {
        Diary diary = diaryRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
        diaryRepository.delete(diary);
    }

    private Diary toDiary(Member member, Book book, DiaryRequest diaryRequest) {
        return new Diary(diaryRequest.content(), member, book, diaryRequest.date());
    }

    private DiaryResponse toDiaryResponse(Diary diary) {
        return new DiaryResponse(diary.getId(), diary.getContent(), diary.getMember().getId(),
                toDiaryBookResponse(diary.getBook()), diary.getDate());
    }

    private DiaryBookResponse toDiaryBookResponse(Book book) {
        return new DiaryBookResponse(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getPublisher(),
                book.getCover(),
                book.getPubDate(),
                book.getLink());
    }
}