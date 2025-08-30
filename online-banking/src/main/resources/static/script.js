// Handle Login
if (document.getElementById("loginForm")) {
  document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
      const response = await fetch("/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });

      if (!response.ok) throw new Error("Invalid credentials");

      const data = await response.json();
      localStorage.setItem("token", data.token);

      window.location.href = "dashboard.html"; // Redirect to dashboard
    } catch (err) {
      document.getElementById("message").innerText = "❌ " + err.message;
    }
  });
}

// Handle Dashboard
if (document.getElementById("accountInfo")) {
  const token = localStorage.getItem("token");

  if (!token) {
    window.location.href = "index.html"; // Redirect if not logged in
  }

  // Fetch account details
  fetch("/accounts", {
    headers: { Authorization: "Bearer " + token }
  })
    .then(res => res.json())
    .then(data => {
      document.getElementById("accountInfo").innerHTML = `
        <p><b>Account Holder:</b> ${data.username}</p>
        <p><b>Balance:</b> $${data.balance}</p>
      `;
    })
    .catch(() => {
      document.getElementById("accountInfo").innerText = "⚠️ Unable to load account info";
    });

  // Handle Transfer
  document.getElementById("transferForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const receiver = document.getElementById("receiver").value;
    const amount = document.getElementById("amount").value;

    try {
      const res = await fetch("/transfer", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token
        },
        body: JSON.stringify({ receiver, amount })
      });

      if (!res.ok) throw new Error("Transfer failed");

      document.getElementById("transferMsg").innerText = "✅ Transfer successful!";
    } catch (err) {
      document.getElementById("transferMsg").innerText = "❌ " + err.message;
    }
  });

  // Logout
  document.getElementById("logoutBtn").addEventListener("click", () => {
    localStorage.removeItem("token");
    window.location.href = "index.html";
  });
}