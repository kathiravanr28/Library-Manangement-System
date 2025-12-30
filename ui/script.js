function hideAll() {
    document.querySelectorAll('.card').forEach(c => c.classList.add('hidden'));
}

function showSection(id) {
    hideAll();
    document.getElementById(id).classList.remove('hidden');
}

/* ---------- Mock Interactions ---------- */
/* (Later you can connect this to Java backend) */

function addBook() {
    if (!isbn.value || !title.value || !author.value) {
        bookMsg.innerText = "All fields required!";
        bookMsg.style.color = "red";
        return;
    }
    bookMsg.innerText = "Book added successfully ✔";
    bookMsg.style.color = "green";
}

function registerMember() {
    if (!mid.value || !mname.value) {
        memberMsg.innerText = "Invalid input!";
        memberMsg.style.color = "red";
        return;
    }
    memberMsg.innerText = "Member registered ✔";
    memberMsg.style.color = "green";
}

function borrowBook() {
    if (!bid.value || !bisbn.value) {
        borrowMsg.innerText = "Missing details!";
        borrowMsg.style.color = "red";
        return;
    }
    borrowMsg.innerText = "Book borrowed ✔";
    borrowMsg.style.color = "green";
}

function returnBook() {
    let fine = days.value > 0 ? days.value * 5 : 0;
    returnMsg.innerText = fine > 0
        ? `Book returned ✔ | Fine: ₹${fine}`
        : "Book returned ✔";
    returnMsg.style.color = "green";
}
