export function redirectToUrl(url: string) {
  if (url.startsWith("/")) {
    throw new Error("redirectToUrl() need full URL!!");
  } else {
    window.location.href = url;
  }
}
