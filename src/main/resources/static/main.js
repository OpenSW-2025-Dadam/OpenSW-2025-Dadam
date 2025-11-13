// 유저 이름 표시
const userName1 = "나희" // 백엔드에서 내려준 값
const userName2 = "수진"
const userName3 = "민규"
document.getElementById("familyName").textContent = `화목한 ${userName1}네`
document.getElementById("user1").textContent = userName1
document.getElementById("user2").textContent = userName2
document.getElementById("user3").textContent = userName3

// 글자수 표시
const answerEl = document.getElementById("answer")
const charCountEl = document.getElementById("charCount")
const commentList = document.getElementById("commentList")
const saveBtn = document.getElementById("saveBtn")

answerEl.addEventListener("input", () => {
  charCountEl.textContent = answerEl.value.length + " / 100"
})

saveBtn.addEventListener("click", () => {
  const text = answerEl.value.trim()
  if (!text) return
  const item = document.createElement("div")
  item.className = "comment-item"
  item.innerHTML = `
    <div>
      <strong>${userName1}</strong> ${text}
      <div class="comment-meta">방금</div>
    </div>
    <div>💬</div>
  `
  commentList.prepend(item)
  answerEl.value = ""
  charCountEl.textContent = "0 / 100"
})


// 2025년 11월 달력
const calendarEl = document.getElementById("calendar")
const year = 2025
const month = 10
const firstDay = new Date(year, month, 1).getDay()
const lastDate = new Date(year, month + 1, 0).getDate()

let cells = ""
for (let i = 0; i < firstDay; i++) cells += `<div></div>`

for (let d = 1; d <= lastDate; d++) {
  let cls = "day-cell"
  if ([3, 7, 14, 21, 28].includes(d)) cls += " light"
  if ([5, 12, 19, 26].includes(d)) cls += " active"
  cells += `<div class="${cls}">${d}</div>`
}

calendarEl.insertAdjacentHTML("beforeend", cells)
