
export default function FreeBoardComponent({boardData}){
    return (
        <>
<div className="fade-in">
                    <h2>자유게시판</h2>
                    <table className="bbsTable">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                        {boardData && boardData.length > 0 ? (
                                boardData.map((data, index)=>{
                                    return <tr key={index}>
                                        <td>{data.no}</td>
                                        <td>{data.title}</td>
                                        <td>{data.author}</td>
                                        <td>{data.date}</td>
                                    </tr>
                                })
                            ):(
                              <>
                              </>
                            )}
                        </tbody>
                       
                    </table>
                </div>
        </>
    )
}