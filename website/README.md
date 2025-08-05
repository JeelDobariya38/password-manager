# 🌐 Passcodes Project Website

This folder contains the **source code for the website** of the [Passcodes Android App](https://github.com/JeelDobariya38/Passcodes/tree/website-production/website), maintained within the same monolithic repository — but in a **separate branch**: `website-production`.

---

## 📦 Project Structure

This repository follows a **monorepo** structure and includes both the Android app and its website:

```

passcodes/
├── app/ # Android app source code (main branch)
├── website/ # Website source code (this folder)
│ └── ... # (code exists only in the 'website-production' branch)
├── README.md # Root project documentation
└── ... # Other Android-specific files

```

---

## 🪄 Why a Separate `website-production` Branch?

The website is deployed using **GitHub Pages**, but it is **not part of the `main` branch** for the following reasons:

- The `main` branch is under **frequent development** for the Android app.
- The website changes **infrequently** and does not need to rebuild every time the app is updated.
- GitHub Pages requires a **stable build source**, and using `main` could trigger accidental or unnecessary deployments.
- The `docs/` folder on `main` is already used for documentation, so it can't be used for website deployment.
- Having a separate branch (`website-production`) for the website ensures that:
  - App development and website deployment are decoupled
  - Rebuilds are minimized
  - Each area remains modular, clean, and easier to manage

---

## 💡 Contributing to the Website

We welcome contributions to improve the website. Please **do not work off of `main`** — it is meant for the Android app.

### 🛠️ Steps to Contribute:

1. **Start from `website-production`**:

   ```bash
   git checkout website-production
   git pull origin website-production
   git checkout -b yourname-website-development
   ```

2. Make your changes inside the `/website` directory
   _(e.g., content, styles, components, etc.)_

3. Commit and push your branch:

   ```bash
   git push origin yourname-website-development
   ```

4. Open a **pull request** targeting the `website-production` branch, **not `main`**.

### ⚠️ Guidelines

> 🚫 Pull requests that contain changes **outside the website code** (e.g., app code) will be rejected or held until they follow repository contribution standards.
> ✅ To contribute to both areas (app & website), see the section below.

---

## 🔄 📂 Contributing to Both App and Website

We understand some contributors may want to work on both the app and the website.

> 🧠 To keep things clean, we recommend handling **each type of contribution separately**.

### ✅ Correct Workflow:

1. **App Contribution:**
   - Branch off from `main` -> `yourname-dev`.
   - Make changes.
   - Open a PR to `main`.

2. **Website Contribution:**
   - Branch off from `website-production`-> `yourname-website-development`.
   - Make your changes.
   - Open a PR to `website-production`.

Keeping these separate ensures:

- Safe and clean version control
- Easier pull request reviews
- No accidental changes to unrelated code

---

## 📫 Questions or Issues?

Please [open an issue](https://github.com/JeelDobariya38/Passcodes/issues) or start a discussion in the [main repository](https://github.com/JeelDobariya38/Passcodes) if you need help contributing or have questions about this structure.
