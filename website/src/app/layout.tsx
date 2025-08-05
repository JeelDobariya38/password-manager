import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Passcodes Website",
  description: "A password manager app.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
